package hu.tb.covidapp.presentation.countryList

import hu.tb.covidapp.domain.model.Country

data class CountryListState(
    val isLoading: Boolean = false,
    val countries: List<Country> = emptyList(),
    val error: String = ""
)
