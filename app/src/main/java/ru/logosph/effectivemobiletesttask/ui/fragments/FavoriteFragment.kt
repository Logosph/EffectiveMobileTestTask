package ru.logosph.effectivemobiletesttask.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.logosph.effectivemobiletesttask.R
import ru.logosph.effectivemobiletesttask.databinding.FragmentFavoriteBinding
import ru.logosph.effectivemobiletesttask.domain.models.SearchItems
import ru.logosph.effectivemobiletesttask.ui.MainActivity
import ru.logosph.effectivemobiletesttask.ui.fragments.adapters.getVacancyDeclension
import ru.logosph.effectivemobiletesttask.ui.fragments.adapters.vacancyAdapterDelegate
import ru.logosph.effectivemobiletesttask.ui.view_models.FavoriteFragmentViewModel

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding by viewBinding<FragmentFavoriteBinding>()
    private val viewModel by viewModel<FavoriteFragmentViewModel>()

    lateinit var adapterVacancies: ListDelegationAdapter<List<SearchItems>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterVacancies = ListDelegationAdapter(
            vacancyAdapterDelegate(
                resources,
                { vacancy ->
            viewModel.deleteVacancy(vacancy)
        }) {
            findNavController().navigate(R.id.action_navigation_favorite_to_vacancyFragment)
        })
        viewModel.loadFavorites()

        lifecycleScope.launch {
            viewModel.vacancies.collect { vacs ->
                adapterVacancies.items = vacs
                adapterVacancies.notifyDataSetChanged()
                binding.countOfVacs.text = getVacancyDeclension(vacs.size)
                val badge = (activity as MainActivity).binding.bottomNavigation.getOrCreateBadge(R.id.navigation_favorite)
                badge.isVisible = if (vacs.isNotEmpty()) {
                    badge.number = vacs.size
                    true
                } else false
            }
        }

        binding.vacsRecycler.adapter = adapterVacancies
        binding.vacsRecycler.layoutManager = LinearLayoutManager(context)
    }
}