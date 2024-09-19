package ru.logosph.effectivemobiletesttask.ui.di

import org.koin.dsl.module
import ru.logosph.effectivemobiletesttask.domain.usecases.DeleteVacancyFromDBUseCase
import ru.logosph.effectivemobiletesttask.domain.usecases.LoadAllDataUseCase
import ru.logosph.effectivemobiletesttask.domain.usecases.LoadAllFavoritesUseCase
import ru.logosph.effectivemobiletesttask.domain.usecases.SaveVacancyToDBUseCase

val domainModule = module {
    factory<LoadAllDataUseCase> { LoadAllDataUseCase(vacanciesApi = get()) }
    factory<LoadAllFavoritesUseCase> { LoadAllFavoritesUseCase(favoriteRepository = get()) }
    factory<SaveVacancyToDBUseCase> { SaveVacancyToDBUseCase(favoriteRepository = get()) }
    factory<DeleteVacancyFromDBUseCase> { DeleteVacancyFromDBUseCase(favoriteRepository = get()) }
}
