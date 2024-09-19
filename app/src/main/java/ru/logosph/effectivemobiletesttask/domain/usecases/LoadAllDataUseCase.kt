package ru.logosph.effectivemobiletesttask.domain.usecases

import ru.logosph.effectivemobiletesttask.domain.interfaces.VacanciesApi
import ru.logosph.effectivemobiletesttask.domain.models.Response

class LoadAllDataUseCase(
    private val vacanciesApi: VacanciesApi
) {
    suspend fun execute(): Response {
        return vacanciesApi.loadAllData() ?: Response()
    }
}