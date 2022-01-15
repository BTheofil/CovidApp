package hu.tb.covidapp.data.repository

import hu.tb.covidapp.data.remote.CovidApi
import hu.tb.covidapp.data.remote.dto.CountryDto
import hu.tb.covidapp.domain.repository.CountryRepository
import javax.inject.Inject

class CovidImpl @Inject constructor(private val api: CovidApi) : CountryRepository{

    override suspend fun getCountryAll(): List<CountryDto> {
        return api.getCountryAll()
    }

    override suspend fun getCountryByName(name: String): CountryDto {
        return api.getCountryByName(name)
    }
}