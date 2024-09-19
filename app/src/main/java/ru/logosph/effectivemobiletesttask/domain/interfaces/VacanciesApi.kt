package ru.logosph.effectivemobiletesttask.domain.interfaces

import ru.logosph.effectivemobiletesttask.domain.models.Response

interface VacanciesApi {

    suspend fun loadAllData(): Response?

}