package quebec.artm.breweryco.presentation.breweries.screens.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import quebec.artm.breweryco.domain.breweries.BreweriesRepository
import quebec.artm.breweryco.domain.breweries.model.Brewery
import quebec.artm.breweryco.presentation.breweries.screens.landing.models.BreweriesScreenViewModelState
import quebec.artm.breweryco.presentation.breweries.screens.landing.models.BreweryUiData
import javax.inject.Inject

@HiltViewModel
class BreweriesScreenViewModel @Inject constructor(
    private val repository: BreweriesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BreweriesScreenViewModelState())
    val state: StateFlow<BreweriesScreenViewModelState> = _state

    init {
        loadBreweries()
    }

    private fun loadBreweries() {
        viewModelScope.launch {

            _state.value = _state.value.copy(isLoading = true)

            val result = repository.getBreweries()

            result.fold(
                onSuccess = { breweries ->
                    onBreweriesFetched(breweries)
                },
                onFailure = { onError(it) }
            )
        }
    }

    private fun onBreweriesFetched(breweries: List<Brewery>) {
        _state.value = _state.value.copy(
            isLoading = false,
            breweries = breweries.map { it.toUiModel() }
        )
    }

    private fun onError(error: Throwable) {
        _state.value = _state.value.copy(
            isLoading = false,
            errorMessage = error.message
        )
    }

    private fun Brewery.toUiModel(): BreweryUiData = BreweryUiData(
        key = id,
        name = name
    )
}