package ru.logosph.effectivemobiletesttask.ui.fragments.adapters

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.logosph.effectivemobiletesttask.databinding.ShowMoreItemBinding
import ru.logosph.effectivemobiletesttask.domain.models.SearchItems
import ru.logosph.effectivemobiletesttask.domain.models.ShowMoreItem

fun showMoreAdapterDelegate(itemClickListener: (ShowMoreItem) -> Unit) =
    adapterDelegateViewBinding<ShowMoreItem, SearchItems, ShowMoreItemBinding>(
        { layoutInflater, root -> ShowMoreItemBinding.inflate(layoutInflater, root, false) }
    ) {

        binding.showMoreButton.setOnClickListener { itemClickListener(item) }

        bind {
            binding.showMoreButton.text = "Eще ${getVacancyDeclension(item.count)}"
        }
    }

fun getVacancyDeclension(number: Int): String {
    val lastDigit = number % 10
    val lastTwoDigits = number % 100

    return when {
        lastTwoDigits in 11..14 -> "$number вакансий"
        lastDigit == 1 -> "$number вакансия"
        lastDigit in 2..4 -> "$number вакансии"
        else -> "$number вакансий"
    }
}