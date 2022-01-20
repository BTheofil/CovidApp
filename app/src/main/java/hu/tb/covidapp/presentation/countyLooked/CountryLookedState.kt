package hu.tb.covidapp.presentation.countyLooked

import hu.tb.covidapp.data.local.entity.CountryEntity

data class CountryLookedState(
    val countries: List<CountryEntity> = emptyList(),
)