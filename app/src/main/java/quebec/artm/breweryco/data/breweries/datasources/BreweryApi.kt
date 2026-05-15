package quebec.artm.breweryco.data.breweries.datasources

import quebec.artm.breweryco.data.breweries.models.BreweryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BreweryApi {

    @GET("breweries")
    suspend fun getBreweries(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<BreweryDto>

    @GET("breweries/{id}")
    suspend fun getBreweryById(
        @Path("id") id: String
    ): BreweryDto
}