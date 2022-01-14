package hu.tb.covidapp.domain.repository

import hu.tb.covidapp.data.remote.dto.SelectedCountryDto

interface CovidRepository {

    suspend fun getSelectedCountry(name: String) : List<SelectedCountryDto>

}