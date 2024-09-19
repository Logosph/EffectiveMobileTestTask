package ru.logosph.effectivemobiletesttask.ui.fragments.adapters

import android.content.res.Resources
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.logosph.effectivemobiletesttask.R
import ru.logosph.effectivemobiletesttask.databinding.VacanciesItemBinding
import ru.logosph.effectivemobiletesttask.domain.models.SearchItems
import ru.logosph.effectivemobiletesttask.domain.models.Vacancy

var favorites = mutableSetOf<Vacancy>()

fun vacancyAdapterDelegate(
    resources: Resources,
    likeClickListener: (Vacancy) -> Unit,
    cardClickListener: () -> Unit
) =
    adapterDelegateViewBinding<Vacancy, SearchItems, VacanciesItemBinding>(
        { layoutInflater, root -> VacanciesItemBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            with(binding) {
                val vacancy: Vacancy = item

                root.setOnClickListener { cardClickListener() }

                favoriteIcon.setOnClickListener {
                    likeClickListener(vacancy)
                }
                favoriteIcon.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        if (vacancy in favorites) R.drawable.favorite_filled else R.drawable.favorite,
                        null
                    )
                )

                if (vacancy.lookingNumber == null) {
                    peopleWatchingText.visibility = View.GONE
                } else {
                    peopleWatchingText.text =
                        "Сейчас просматривает ${getPersonDeclension(vacancy.lookingNumber)}"
                }
                vacancyTitle.text = vacancy.title

                if (vacancy.salary != null && vacancy.salary.short != null) {
                    salaryText.text = vacancy.salary.short
                } else {
                    salaryText.visibility = View.GONE
                }
                if (vacancy.address != null) {
                    cityName.text = vacancy.address.town
                } else {
                    cityName.visibility = View.GONE
                }

                if (vacancy.company != null) {
                    companyName.text = vacancy.company
                } else {
                    companyName.visibility = View.GONE
                }

                if (vacancy.experience != null) {
                    experienceText.text = vacancy.experience.previewText
                } else {
                    experienceText.visibility = View.GONE
                }

                if (vacancy.publishedDate != null) {
                    val date = vacancy.publishedDate.split("-")
                    val text =
                        "Опубликовано ${date[2].trim('0')} ${getMonthByNumber(date[1].toInt())}"
                    publishedDate.text = text
                } else {
                    publishedDate.visibility = View.GONE
                }
            }
        }
    }

fun getPersonDeclension(number: Int): String {
    val lastDigit = number % 10
    val lastTwoDigits = number % 100

    return when {
        lastTwoDigits in 11..14 -> "$number человек"
        lastDigit == 1 -> "$number человек"
        lastDigit in 2..4 -> "$number человека"
        else -> "$number человек"
    }
}

fun getMonthByNumber(number: Int) = when (number) {
    1 -> "января"
    2 -> "февраля"
    3 -> "марта"
    4 -> "апреля"
    5 -> "мая"
    6 -> "июня"
    7 -> "июля"
    8 -> "августа"
    9 -> "сентября"
    10 -> "октября"
    11 -> "ноября"
    12 -> "декабря"
    else -> ""
}
