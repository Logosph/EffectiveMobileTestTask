package ru.logosph.effectivemobiletesttask.ui.fragments

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.logosph.effectivemobiletesttask.R
import ru.logosph.effectivemobiletesttask.databinding.FragmentSearchBinding
import ru.logosph.effectivemobiletesttask.domain.models.Offer
import ru.logosph.effectivemobiletesttask.domain.models.SearchItems
import ru.logosph.effectivemobiletesttask.domain.models.ShowMoreItem
import ru.logosph.effectivemobiletesttask.ui.MainActivity
import ru.logosph.effectivemobiletesttask.ui.NetworkStates
import ru.logosph.effectivemobiletesttask.ui.fragments.adapters.getVacancyDeclension
import ru.logosph.effectivemobiletesttask.ui.fragments.adapters.offerAdapterDelegate
import ru.logosph.effectivemobiletesttask.ui.fragments.adapters.showMoreAdapterDelegate
import ru.logosph.effectivemobiletesttask.ui.fragments.adapters.vacancyAdapterDelegate
import ru.logosph.effectivemobiletesttask.ui.view_models.SearchFragmentViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    private val viewModel by viewModel<SearchFragmentViewModel>()

    lateinit var adapterVacancies: ListDelegationAdapter<List<SearchItems>>
    lateinit var adapterOffers: ListDelegationAdapter<List<Offer>>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        adapterVacancies = ListDelegationAdapter(
            vacancyAdapterDelegate(
                resources,
                { vacancy ->
                    if (viewModel.checkFavorite(vacancy)) viewModel.deleteVacancyFromDB(vacancy) else viewModel.saveVacancyToDB(
                        vacancy
                    )
                    viewModel.loadVacancies()
                }) {
                findNavController().navigate(R.id.action_navigation_search_to_vacancyFragment)
            },
            showMoreAdapterDelegate {
                setTitle(false)
                requireActivity().onBackPressedDispatcher.addCallback(object :
                    OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        setTitle(true)
                        remove()
                    }
                })
            }
        )

        adapterOffers = ListDelegationAdapter(offerAdapterDelegate(resources) { offer ->
            val url = offer.link
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        })

        with(binding) {
            vacanciesRecyclerView.layoutManager = LinearLayoutManager(context)
            vacanciesRecyclerView.adapter = adapterVacancies
            progressBar.visibility = View.GONE

            offersRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            offersRecyclerView.adapter = adapterOffers
        }


        lifecycleScope.launch {
            viewModel.vacancies.collect { vacancies ->
                adapterVacancies.apply {
                    updateAdapter()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.offers.collect { offers ->
                if (offers.isEmpty()) {
                    binding.offersRecyclerView.visibility = View.GONE
                    return@collect
                }
                binding.offersRecyclerView.visibility = View.VISIBLE
                adapterOffers.apply {
                    items = offers
                    notifyDataSetChanged()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.loadingState.collect loadingVacs@{ state ->

                if (state == NetworkStates.LOADING) {
                    binding.progressBar.visibility = View.VISIBLE
                    (activity as MainActivity).binding.bottomNavigation.visibility = View.GONE
                    binding.blockingView.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                    (activity as MainActivity).binding.bottomNavigation.visibility = View.VISIBLE
                    binding.blockingView.visibility = View.GONE
                    val badge = (activity as MainActivity).binding.bottomNavigation.getOrCreateBadge(R.id.navigation_favorite)
                    badge.isVisible = if (viewModel.favorites.value.isNotEmpty()) {
                        badge.number = viewModel.favorites.value.size
                        true
                    } else false


                    val message: String = when (state) {
                        NetworkStates.SUCCESS, NetworkStates.LOADING -> ""
                        NetworkStates.TIMEOUT_EXCEPTION -> "Время ожидания запроса истекло. Повторите попытку позже."
                        NetworkStates.CONNECTION_EXCEPTION -> "Нет сети. Проверьте соединение с интернетом."
                        NetworkStates.UNEXPECTED_ERROR -> "Непредвиденная ошибка. Повторите попытку позже или обратитесь к разработчикам."
                    }
                    if (message != "") Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.favorites.collect {
                ru.logosph.effectivemobiletesttask.ui.fragments.adapters.favorites =
                    it.toMutableSet()
                adapterVacancies.notifyDataSetChanged()
            }
        }

        viewModel.loadVacancies()
    }

    private fun updateAdapter(full: Boolean = false) {
        val vacancies = viewModel.vacancies.value
        if (full) {
            adapterVacancies.items = viewModel.vacancies.value
        } else {
            adapterVacancies.apply {
                if (vacancies.isEmpty() || vacancies.size <= 3) items = vacancies
                else {
                    val vacs = vacancies.slice(0..2)
                    items = List(vacs.size + 1) { index ->
                        if (index == vacs.size) ShowMoreItem(vacancies.size - vacs.size) else vacs[index]
                    }
                }
            }
        }
        adapterVacancies.notifyDataSetChanged()
        binding.blockingView.setOnClickListener {}

    }

    private fun setTitle(short: Boolean) {
        with(binding) {
            if (short) {
                offersRecyclerView.visibility = View.VISIBLE
                vacsForYouTextView.visibility = View.VISIBLE

                countOfVacs.visibility = View.GONE
                additionalFilter.visibility = View.GONE
                additionalFilterIcon.visibility = View.GONE

                searchIcon.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.search,
                        null
                    )
                )
                searchIcon.imageTintList = ColorStateList(
                    arrayOf(intArrayOf()),
                    intArrayOf(ResourcesCompat.getColor(resources, R.color.basicGrey3, null))
                )
                searchIcon.setOnClickListener { }

                searchEditText.hint = "Должность, ключевые слова"

                updateAdapter()
            } else {
                offersRecyclerView.visibility = View.GONE
                vacsForYouTextView.visibility = View.GONE

                countOfVacs.visibility = View.VISIBLE
                countOfVacs.text = getVacancyDeclension(viewModel.vacancies.value.size)
                additionalFilter.visibility = View.VISIBLE
                additionalFilterIcon.visibility = View.VISIBLE

                searchIcon.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.back_arrow,
                        null
                    )
                )
                searchIcon.imageTintList = ColorStateList(
                    arrayOf(intArrayOf()),
                    intArrayOf(ResourcesCompat.getColor(resources, R.color.basicWhite, null))
                )
                searchIcon.setOnClickListener {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }

                searchEditText.hint = "Должность по подходящим вакансиям"

                updateAdapter(true)
            }
        }
    }
}