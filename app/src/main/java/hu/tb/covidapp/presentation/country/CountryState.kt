package hu.tb.covidapp.presentation.country

import hu.tb.covidapp.domain.model.Country

data class CountryState(
    val isLoading: Boolean = false,
    val country: Country? = null,
    val error: String = ""
)
