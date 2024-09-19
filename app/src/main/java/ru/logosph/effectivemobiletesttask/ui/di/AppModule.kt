package ru.logosph.effectivemobiletesttask.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.logosph.effectivemobiletesttask.ui.view_models.FavoriteFragmentViewModel
import ru.logosph.effectivemobiletesttask.ui.view_models.SearchFragmentViewModel

val appModule = module {
    viewModel {
        SearchFragmentViewModel(
            loadAllDataUseCase = get(),
            saveVacancyToDBUseCase = get(),
            deleteVacancyFromDBUseCase = get(),
            loadAllFavoritesUseCase = get()
        )
    }

    viewModel {
        FavoriteFragmentViewModel(
            loadAllFavoritesUseCase = get(),
            deleteVacancyFromDBUseCase = get()
        )
    }
}

