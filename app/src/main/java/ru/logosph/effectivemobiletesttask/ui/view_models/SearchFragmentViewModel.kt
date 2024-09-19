package ru.logosph.effectivemobiletesttask.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.logosph.effectivemobiletesttask.domain.usecases.LoadAllDataUseCase
import ru.logosph.effectivemobiletesttask.domain.models.Offer
import ru.logosph.effectivemobiletesttask.domain.models.Vacancy
import ru.logosph.effectivemobiletesttask.domain.usecases.DeleteVacancyFromDBUseCase
import ru.logosph.effectivemobiletesttask.domain.usecases.LoadAllFavoritesUseCase
import ru.logosph.effectivemobiletesttask.domain.usecases.SaveVacancyToDBUseCase
import ru.logosph.effectivemobiletesttask.ui.NetworkStates
import java.net.ConnectException
import java.net.SocketTimeoutException

class SearchFragmentViewModel(
    private val loadAllDataUseCase: LoadAllDataUseCase,
    private val saveVacancyToDBUseCase: SaveVacancyToDBUseCase,
    private val deleteVacancyFromDBUseCase: DeleteVacancyFromDBUseCase,
    private val loadAllFavoritesUseCase: LoadAllFavoritesUseCase
) : ViewModel() {

    private val _offers: MutableStateFlow<List<Offer>> =
        MutableStateFlow(emptyList())

    val offers: StateFlow<List<Offer>>
        get() = _offers

    private val _vacancies: MutableStateFlow<List<Vacancy>> =
        MutableStateFlow(emptyList())

    val vacancies: StateFlow<List<Vacancy>>
        get() = _vacancies

    private val _loadingState: MutableStateFlow<NetworkStates> =
        MutableStateFlow(NetworkStates.LOADING)

    val loadingState: StateFlow<NetworkStates>
        get() = _loadingState


    private val _favorites = MutableStateFlow<List<Vacancy>>(emptyList())
    val favorites: StateFlow<List<Vacancy>>
        get() = _favorites

    fun loadVacancies() {
        viewModelScope.launch {
            _loadingState.emit(NetworkStates.LOADING)
            try {
                withContext(Dispatchers.IO) {
                    val vacs = async { loadAllDataUseCase.execute() }
                    val response = vacs.await()
                    _vacancies.value = response.vacancies ?: emptyList()
                    _offers.value = response.offers ?: emptyList()
                    loadFavorites()
                    _loadingState.emit(NetworkStates.SUCCESS)
                }
            } catch (e: SocketTimeoutException) {
                _loadingState.emit(NetworkStates.TIMEOUT_EXCEPTION)
            } catch (e: ConnectException) {
                _loadingState.emit(NetworkStates.CONNECTION_EXCEPTION)
            } catch (e: Exception) {
                _loadingState.emit(NetworkStates.UNEXPECTED_ERROR)
            }
        }
    }

    fun saveVacancyToDB(vacancy: Vacancy) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                saveVacancyToDBUseCase.execute(vacancy)
            }
        }
    }

    fun deleteVacancyFromDB(vacancy: Vacancy) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deleteVacancyFromDBUseCase.execute(vacancy)
            }
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _favorites.value = loadAllFavoritesUseCase.execute()
            }
        }
    }

    fun checkFavorite(vacancy: Vacancy) = _favorites.value.contains(vacancy)
}