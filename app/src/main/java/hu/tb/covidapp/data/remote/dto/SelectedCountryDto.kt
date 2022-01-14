package hu.tb.covidapp.data.remote.dto

import hu.tb.covidapp.domain.model.SelectedCountry

data class SelectedCountryDto(
    val code: String,
    val confirmed: Int,
    val country: String,
    val critical: Int,
    val deaths: Int,
    val lastChange: String,
    val lastUpdate: String,
    val latitude: Double,
    val longitude: Double,
    val recovered: Int
)

fun SelectedCountryDto.toSelectedCountry() : SelectedCountry{
    return SelectedCountry(
        country = country,
        confirmed = confirmed,
        critical = critical,
        deaths = deaths,
        recovered = recovered
    )
}