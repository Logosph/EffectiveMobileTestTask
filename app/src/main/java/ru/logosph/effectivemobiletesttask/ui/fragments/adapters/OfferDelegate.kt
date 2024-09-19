package ru.logosph.effectivemobiletesttask.ui.fragments.adapters

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.logosph.effectivemobiletesttask.R
import ru.logosph.effectivemobiletesttask.databinding.OffersItemBinding
import ru.logosph.effectivemobiletesttask.domain.models.Offer

fun offerAdapterDelegate(
    resources: Resources,
    itemClickListener: (Offer) -> Unit
) =
    adapterDelegateViewBinding<Offer, Offer, OffersItemBinding>(
        { layoutInflater, root -> OffersItemBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            with(binding) {

                val offer = item

                root.setOnClickListener { itemClickListener(offer) }

                offerTitle.text = offer.title!!.trim(' ')
                if (offer.button != null) {
                    offerButton.text = offer.button.text!!.trim(' ')
                    offerTitle.maxLines = 2
                } else {
                    offerButton.visibility = View.GONE
                    offerTitle.maxLines = 3
                }
                var color = -1
                var bgColor = -1
                var icon: Drawable? = null

                when (offer.id) {
                    "near_vacancies" -> {
                        color = colors["specialBlue"]!!
                        bgColor = colors["specialDarkBlue"]!!
                        icon = ResourcesCompat.getDrawable(resources, R.drawable.point, null)
                    }

                    "level_up_resume" -> {
                        color = colors["specialGreen"]!!
                        bgColor = colors["specialDrakGreen"]!!
                        icon = ResourcesCompat.getDrawable(resources, R.drawable.star, null)
                    }

                    "temporary_job" -> {
                        color = colors["specialGreen"]!!
                        bgColor = colors["specialDrakGreen"]!!
                        icon = ResourcesCompat.getDrawable(resources, R.drawable.vacancies, null)
                    }

                    else -> {
                        offerImage.visibility = View.GONE
                    }
                }
                if (color != -1) {
                    offerImage.imageTintList =
                        ColorStateList(arrayOf(intArrayOf()), intArrayOf(color))
                    offerImage.backgroundTintList =
                        ColorStateList(arrayOf(intArrayOf()), intArrayOf(bgColor))
                    offerImage.setImageDrawable(icon)
                }
            }
        }
    }

val colors = mapOf(
    "specialBlue" to Color.parseColor("#2B7EFE"),
    "specialDarkBlue" to Color.parseColor("#00427D"),
    "specialGreen" to Color.parseColor("#4CB24E"),
    "specialDrakGreen" to Color.parseColor("#015905"),
)