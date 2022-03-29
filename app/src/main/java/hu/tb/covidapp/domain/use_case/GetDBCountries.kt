package hu.tb.covidapp.domain.use_case

import androidx.lifecycle.LiveData
import hu.tb.covidapp.data.local.entity.CountryEntity
import hu.tb.covidapp.domain.repository.CountryEntityRepository

class GetDBCountries(private val repository: CountryEntityRepository) {

    operator fun invoke() : LiveData<List<CountryEntity>> {
        return repository.getCounties()
    }
}