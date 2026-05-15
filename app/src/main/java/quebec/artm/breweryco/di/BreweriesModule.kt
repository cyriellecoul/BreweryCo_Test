package quebec.artm.breweryco.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quebec.artm.breweryco.data.breweries.BreweriesRepositoryImpl
import quebec.artm.breweryco.data.breweries.datasources.BreweryApi
import quebec.artm.breweryco.data.breweries.datasources.RemoteBreweriesDataSource
import quebec.artm.breweryco.domain.breweries.BreweriesRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BreweriesModule {

    @Binds
    abstract fun bindBreweriesRepository(
        impl: BreweriesRepositoryImpl
    ): BreweriesRepository

    companion object {

        @Provides
        @Singleton
        fun provideBreweryApi(
            retrofit: Retrofit
        ): BreweryApi {
            return retrofit.create(BreweryApi::class.java)
        }

        @Provides
        @Singleton
        fun provideRemoteDataSource(
            api: BreweryApi
        ): RemoteBreweriesDataSource {
            return RemoteBreweriesDataSource(api)
        }
    }
}