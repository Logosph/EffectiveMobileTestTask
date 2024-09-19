package ru.logosph.effectivemobiletesttask.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    val id: String? = null,
    val title: String? = null,
    val link: String? = null,
    val button: Button? = null
)

@Serializable
data class Button(
    val text: String? = null
)
