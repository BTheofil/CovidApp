package hu.tb.covidapp.presentation.countryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tb.covidapp.common.Resource
import hu.tb.covidapp.domain.use_case.GetCountryAllUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(private val getCountyAllUseCase: GetCountryAllUseCase) : ViewModel(){

    private val _state = MutableStateFlow(CountryListState())
    val state: StateFlow<CountryListState> = _state

    init {
        getCountries()
    }

    private fun getCountries(){
        getCountyAllUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CountryListState(countries = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CountryListState(error = result.message ?: " An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CountryListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}