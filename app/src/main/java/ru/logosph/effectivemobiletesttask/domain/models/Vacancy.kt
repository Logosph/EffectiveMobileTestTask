package ru.logosph.effectivemobiletesttask.domain.models

import kotlinx.serialization.Serializable

sealed class SearchItems

@Serializable
data class Vacancy(
    val id: String? = null,
    val lookingNumber: Int? = null,
    val title: String? = null,
    val address: Address? = null,
    val company: String? = null,
    val experience: Experience? = null,
    val publishedDate: String? = null,
    val salary: Salary? = null,
    val schedules: List<String>? = null,
    val appliedNumber: Int? = null,
    val description: String? = null,
    val responsibilities: String? = null,
    val questions: List<String>? = null,
): SearchItems()

@Serializable
data class Address(
    val town: String? = null,
    val street: String? = null,
    val house: String? = null
)

@Serializable
data class Experience(
    val previewText: String? = null,
    val text: String? = null
)

@Serializable
data class Salary(
    val full: String? = null,
    val short: String? = null
)

data class ShowMoreItem(
    val count: Int
): SearchItems()
