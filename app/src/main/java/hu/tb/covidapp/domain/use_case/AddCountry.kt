package hu.tb.covidapp.domain.use_case

import hu.tb.covidapp.data.local.entity.CountryEntity
import hu.tb.covidapp.domain.repository.CountryEntityRepository

class AddCountry(private val repository: CountryEntityRepository) {

    suspend operator fun invoke(countryEntity: CountryEntity){
        repository.insertCountry(countryEntity)
    }
}