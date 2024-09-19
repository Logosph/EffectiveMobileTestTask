package ru.logosph.effectivemobiletesttask.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.logosph.effectivemobiletesttask.R
import ru.logosph.effectivemobiletesttask.databinding.FragmentVacancyBinding

class VacancyFragment : Fragment(R.layout.fragment_vacancy) {

    private val binding by viewBinding<FragmentVacancyBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}