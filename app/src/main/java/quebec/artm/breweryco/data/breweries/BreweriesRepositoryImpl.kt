package quebec.artm.breweryco.data.breweries

import quebec.artm.breweryco.data.breweries.datasources.RemoteBreweriesDataSource
import quebec.artm.breweryco.data.breweries.models.BreweryDto
import quebec.artm.breweryco.domain.breweries.BreweriesRepository
import quebec.artm.breweryco.domain.breweries.model.Brewery
import quebec.artm.breweryco.domain.breweries.model.BreweryType
import javax.inject.Inject

class BreweriesRepositoryImpl @Inject constructor(
    private val remote: RemoteBreweriesDataSource
) : BreweriesRepository {

    override suspend fun getBreweries(): Result<List<Brewery>> {
        return try {
            val data = remote.getBreweries(1, 20)
            Result.success(data.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBreweryById(id: String): Result<Brewery> {
        return try {
            val data = remote.getBreweryById(id)
            Result.success(data.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

private fun BreweryDto.toDomain(): Brewery {
    return Brewery(
        id = this.id!!,
        name = this.name!!,
        address1 = this.address1 ?: "",
        type = BreweryType.fromId(this.breweryType!!),
        latitude = this.latitude ?: 0.0,
        longitude = this.longitude ?: 0.0,
        phone = this.phone ?: "",
        website_url = this.websiteUrl ?: "",
        country = this.country!!,
    )
}