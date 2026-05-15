package quebec.artm.breweryco.data.breweries.datasources

import javax.inject.Inject

class RemoteBreweriesDataSource @Inject constructor(
    private val api: BreweryApi
) {

    suspend fun getBreweries(page: Int, perPage: Int) =
        api.getBreweries(page, perPage)

    suspend fun getBreweryById(id: String) =
        api.getBreweryById(id)
}