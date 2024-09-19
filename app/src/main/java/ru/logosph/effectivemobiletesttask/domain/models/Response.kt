package ru.logosph.effectivemobiletesttask.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val offers: List<Offer>? = null,
    val vacancies: List<Vacancy>? = null
)
