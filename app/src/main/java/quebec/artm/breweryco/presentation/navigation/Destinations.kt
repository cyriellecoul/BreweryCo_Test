package quebec.artm.breweryco.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Destinations {

    @Serializable
    data object Home : Destinations

    @Serializable
    data class Details(val id: String) : Destinations
}