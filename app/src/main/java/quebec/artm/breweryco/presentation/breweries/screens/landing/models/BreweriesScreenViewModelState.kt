package quebec.artm.breweryco.presentation.breweries.screens.landing.models

data class BreweriesScreenViewModelState(
    val breweries: List<BreweryUiData> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)