package hu.tb.covidapp.presentation.country

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tb.covidapp.common.Constants
import hu.tb.covidapp.common.Resource
import hu.tb.covidapp.domain.use_case.GetCountyUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val getCountyUseCase: GetCountyUseCase, savedStateHandle: SavedStateHandle) : ViewModel(){

    private val _state = mutableStateOf(CountryState())
    val state: State<CountryState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COUNTRY_NAME)?.let { countryName ->
            getCountries(countryName)
        }
    }

    private fun getCountries(countryName: String){
        getCountyUseCase(countryName).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CountryState(country = result.data)
                }
                is Resource.Error -> {
                    _state.value = CountryState(error = result.message ?: " An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CountryState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}