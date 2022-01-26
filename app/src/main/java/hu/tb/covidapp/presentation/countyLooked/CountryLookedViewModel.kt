package hu.tb.covidapp.presentation.countyLooked

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tb.covidapp.data.local.entity.CountryEntity
import hu.tb.covidapp.domain.use_case.CountryUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryLookedViewModel@Inject constructor(countryUseCases: CountryUseCases) : ViewModel() {

    val readAllData: LiveData<List<CountryEntity>> = countryUseCases.getDBCountries()

}