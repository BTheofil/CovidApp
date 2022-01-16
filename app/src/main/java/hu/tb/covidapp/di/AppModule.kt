package hu.tb.covidapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.tb.covidapp.common.Constants
import hu.tb.covidapp.data.local.CountryDatabase
import hu.tb.covidapp.data.local.repository.CountryEntityImpl
import hu.tb.covidapp.data.remote.CovidApi
import hu.tb.covidapp.data.repository.CovidImpl
import hu.tb.covidapp.domain.repository.CountryEntityRepository
import hu.tb.covidapp.domain.repository.CountryRepository
import hu.tb.covidapp.domain.use_case.AddCountry
import hu.tb.covidapp.domain.use_case.CountryUseCases
import hu.tb.covidapp.domain.use_case.GetDBCountries
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

    @Provides
    @Singleton
    fun provideCountryDatabase(app: Application): CountryDatabase {
        return Room.databaseBuilder(
            app,
            CountryDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCountryRepository(db: CountryDatabase): CountryEntityRepository {
        return CountryEntityImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideCountryUseCases(repository: CountryEntityRepository): CountryUseCases {
        return CountryUseCases(
            getDBCountries = GetDBCountries(repository),
            addCountry = AddCountry(repository),
        )
    }
}