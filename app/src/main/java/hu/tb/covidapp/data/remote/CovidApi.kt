package hu.tb.covidapp.data.remote

import hu.tb.covidapp.data.remote.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApi {

    @GET("/country/all")
    suspend fun getCountryAll() : List<CountryDto>

    @GET("/country{name}")
    suspend fun getCountryByName(@Path("name") name: String) : CountryDto
}