package hu.tb.covidapp.domain.repository

import hu.tb.covidapp.data.remote.dto.CountryDto

interface CountryRepository {

    suspend fun getCountryAll() : List<CountryDto>

    suspend fun getCountryByName(name: String) : CountryDto

}