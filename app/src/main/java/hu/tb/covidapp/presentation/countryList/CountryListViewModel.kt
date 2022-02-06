package hu.tb.covidapp.presentation.countryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tb.covidapp.common.Resource
import hu.tb.covidapp.data.local.entity.CountryEntity
import hu.tb.covidapp.domain.model.Country
import hu.tb.covidapp.domain.use_case.CountryUseCases
import hu.tb.covidapp.domain.use_case.GetCountryAllUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getCountyAllUseCase: GetCountryAllUseCase,
    private val countryUseCases: CountryUseCases,
) : ViewModel() {

    private val _state = MutableStateFlow<CountryListState>(CountryListState.Empty)
    val state: StateFlow<CountryListState> = _state

    init {
        getCountries()
    }

    private fun getCountries() {
        getCountyAllUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        CountryListState.Success(result.data!!)
                }
                is Resource.Error -> {
                    _state.value = CountryListState.Error(result.message!!)
                }
                is Resource.Loading -> {
                    _state.value = CountryListState.Loading
                }
            }
        }.launchIn(viewModelScope)
    }

    fun insertCountryToDb(country: Country) {
        viewModelScope.launch(Dispatchers.IO) {
            countryUseCases.addCountry.addCountry(
                CountryEntity(
                    country = country.country,
                    critical = country.critical,
                    confirmed = country.confirmed,
                    recovered = country.recovered,
                    deaths = country.deaths
                )
            )
        }
    }
}