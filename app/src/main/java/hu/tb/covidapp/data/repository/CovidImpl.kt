package hu.tb.covidapp.data.repository

import hu.tb.covidapp.data.remote.CovidApi
import hu.tb.covidapp.data.remote.dto.SelectedCountryDto
import hu.tb.covidapp.domain.repository.CovidRepository
import javax.inject.Inject

class CovidImpl @Inject constructor(private val api: CovidApi) : CovidRepository{

    override suspend fun getSelectedCountry(name: String): List<SelectedCountryDto> {
        return api.getSelectedCountry(name)
    }
}