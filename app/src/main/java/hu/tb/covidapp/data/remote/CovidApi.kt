package hu.tb.covidapp.data.remote

import hu.tb.covidapp.data.remote.dto.SelectedCountryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApi {

    @GET("/country{name}")
    suspend fun getSelectedCountry(@Path("name") name: String) : List<SelectedCountryDto>
}