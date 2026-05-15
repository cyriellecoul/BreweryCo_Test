package quebec.artm.breweryco.presentation.breweries.screens.landing

import androidx.lifecycle.ViewModel
import quebec.artm.breweryco.domain.breweries.model.Brewery

class BreweryViewModel : ViewModel() {

    fun getBreweryById(id: String): Brewery? {
        return breweries.find { it.id == id }
    }

    val breweries: List<Brewery> = emptyList()
}