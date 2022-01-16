package hu.tb.covidapp.domain.repository

import hu.tb.covidapp.data.local.entity.CountryEntity
interface CountryEntityRepository {

    fun getCounties(): List<CountryEntity>

    suspend fun getCountryById(id: Int): CountryEntity?

    suspend fun insertCountry(countryEntity: CountryEntity)

    suspend fun deleteCountry(countryEntity: CountryEntity)
}