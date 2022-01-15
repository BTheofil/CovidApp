package hu.tb.covidapp.data.remote.dto

import hu.tb.covidapp.domain.model.Country

data class CountryDto(
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

fun CountryDto.toCountry() : Country{
    return Country(
        country = country,
        confirmed = confirmed,
        critical = critical,
        deaths = deaths,
        recovered = recovered
    )
}