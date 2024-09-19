package ru.logosph.effectivemobiletesttask.domain.usecases

import ru.logosph.effectivemobiletesttask.domain.interfaces.FavoriteRepository
import ru.logosph.effectivemobiletesttask.domain.models.toVacancy

class LoadAllFavoritesUseCase(val favoriteRepository: FavoriteRepository) {
    suspend fun execute() = favoriteRepository.getAll().map { it.toVacancy() }
}