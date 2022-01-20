package hu.tb.covidapp.domain.repository

import hu.tb.covidapp.data.local.entity.CountryEntity
import kotlinx.coroutines.flow.Flow

interface CountryEntityRepository {

    fun getCounties(): Flow<List<CountryEntity>>

    suspend fun getCountryById(id: Int): CountryEntity?

    suspend fun insertCountry(countryEntity: CountryEntity)

    suspend fun deleteCountry(countryEntity: CountryEntity)
}