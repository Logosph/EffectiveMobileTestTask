package ru.logosph.effectivemobiletesttask.domain.usecases

import ru.logosph.effectivemobiletesttask.domain.interfaces.FavoriteRepository
import ru.logosph.effectivemobiletesttask.domain.models.Vacancy
import ru.logosph.effectivemobiletesttask.domain.models.toFavoriteEntity

class SaveVacancyToDBUseCase(val favoriteRepository: FavoriteRepository) {
    suspend fun execute(vacancy: Vacancy) {
        favoriteRepository.insert(vacancy.toFavoriteEntity())
    }
}