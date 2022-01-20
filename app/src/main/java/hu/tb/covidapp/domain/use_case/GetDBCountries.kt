package hu.tb.covidapp.domain.use_case

import hu.tb.covidapp.data.local.entity.CountryEntity
import hu.tb.covidapp.domain.repository.CountryEntityRepository
import kotlinx.coroutines.flow.Flow


class GetDBCountries(private val repository: CountryEntityRepository) {

    operator fun invoke() : Flow<List<CountryEntity>> {
        return repository.getCounties()
    }
}