package hu.tb.covidapp.domain.repository

import androidx.lifecycle.LiveData
import hu.tb.covidapp.data.local.entity.CountryEntity

interface CountryEntityRepository {

    fun getCounties(): LiveData<List<CountryEntity>>

    suspend fun getCountryById(id: Int): CountryEntity?

    suspend fun insertCountry(countryEntity: CountryEntity)

    suspend fun deleteCountry(countryEntity: CountryEntity)
}