package ru.logosph.effectivemobiletesttask.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.logosph.effectivemobiletesttask.domain.models.Vacancy
import ru.logosph.effectivemobiletesttask.domain.usecases.DeleteVacancyFromDBUseCase
import ru.logosph.effectivemobiletesttask.domain.usecases.LoadAllFavoritesUseCase

class FavoriteFragmentViewModel(
    private val loadAllFavoritesUseCase: LoadAllFavoritesUseCase,
    private val deleteVacancyFromDBUseCase: DeleteVacancyFromDBUseCase
) : ViewModel() {

    private val _vacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancies: StateFlow<List<Vacancy>>
        get() = _vacancies

    fun loadFavorites() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val vacs = loadAllFavoritesUseCase.execute().map { it }
                _vacancies.value = vacs
            }

        }
    }

    fun deleteVacancy(vacancy: Vacancy) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deleteVacancyFromDBUseCase.execute(vacancy)
                loadFavorites()
            }
        }
    }
}