package hu.tb.covidapp.presentation.countyLooked

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tb.covidapp.domain.use_case.CountryUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryLookedViewModel@Inject constructor(private var countryUseCases: CountryUseCases) : ViewModel() {

    private val _state = MutableStateFlow(CountryLookedState())
    val state : StateFlow<CountryLookedState> = _state

    private var getNotesJob: Job? = null

    init {
        getNotes()
    }

    private fun getNotes() {
        getNotesJob?.cancel()
        getNotesJob = countryUseCases.getDBCountries()
            .onEach { country ->
                _state.value = state.value.copy(
                    countries = country
                )
            }
            .launchIn(viewModelScope)
    }

}