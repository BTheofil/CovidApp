package hu.tb.covidapp.presentation.covid_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.tb.covidapp.common.Constants
import hu.tb.covidapp.common.Resource
import hu.tb.covidapp.domain.use_case.GetCovidUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CovidListViewModel @Inject constructor(private val getCovidUseCase: GetCovidUseCase, savedStateHandle: SavedStateHandle) : ViewModel(){

    private val _state = mutableStateOf(CovidListState())
    val state: State<CovidListState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COUNTRY_NAME)?.let { countryName ->
            getCountries(countryName)
        }
    }

    private fun getCountries(countryName: String){
        getCovidUseCase(countryName).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CovidListState(countries = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CovidListState(error = result.message ?: " An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CovidListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}