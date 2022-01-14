package hu.tb.covidapp.presentation.covid_list

import hu.tb.covidapp.domain.model.SelectedCountry

data class CovidListState(
    val isLoading: Boolean = false,
    val countries: List<SelectedCountry> = emptyList(),
    val error: String = ""
)
