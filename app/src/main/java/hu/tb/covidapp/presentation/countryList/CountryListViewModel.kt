package hu.tb.covidapp.presentation.countryList

import android.util.Log
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
class CountryListViewModel @Inject constructor(private val getCountyAllUseCase: GetCountryAllUseCase, private val countryUseCases: CountryUseCases) : ViewModel(){

    private val _state = MutableStateFlow(CountryListState())
    val state: StateFlow<CountryListState> = _state

    init {
        getCountries()
    }

    private fun getCountries(){
        getCountyAllUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CountryListState(countries = result.data ?: emptyList(), isLoading = false)
                }
                is Resource.Error -> {
                    _state.value = CountryListState(error = result.message ?: " An unexpected error occurred", isLoading = false)
                }
                is Resource.Loading -> {
                    _state.value = CountryListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun insertCountryToDb(country: Country){
        viewModelScope.launch(Dispatchers.IO) {
            countryUseCases.addCountry.addCountry(CountryEntity(
                country = country.country,
                critical = country.critical,
                confirmed = country.confirmed,
                recovered = country.recovered,
                deaths = country.deaths
            ))
        }
    }
}