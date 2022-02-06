package hu.tb.covidapp.presentation.countryList

import hu.tb.covidapp.domain.model.Country

sealed class CountryListState {
    object Empty: CountryListState()
    object Loading: CountryListState()
    data class Success(val data: List<Country>) : CountryListState()
    data class Error(val message: String) : CountryListState()
}
