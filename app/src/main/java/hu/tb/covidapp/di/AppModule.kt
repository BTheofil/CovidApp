package hu.tb.covidapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.tb.covidapp.common.Constants
import hu.tb.covidapp.data.remote.CovidApi
import hu.tb.covidapp.data.repository.CovidImpl
import hu.tb.covidapp.domain.repository.CountryRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCovidApi(): CovidApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CovidApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCovidRepository(api: CovidApi) : CountryRepository {
        return CovidImpl(api)
    }
}