package hu.tb.covidapp.domain.use_case

import hu.tb.covidapp.common.Resource
import hu.tb.covidapp.data.local.entity.toCountry
import hu.tb.covidapp.domain.model.Country
import hu.tb.covidapp.domain.repository.CountryEntityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDBCountries(private val repository: CountryEntityRepository) {

    operator fun invoke() : Flow<Resource<List<Country>>> = flow {
        try {
            emit(Resource.Loading())
            val countries = repository.getCounties().map { it.toCountry() }
            emit(Resource.Success(countries))
        } catch (e: Exception){
            emit(Resource.Error(e.localizedMessage ?: "Can not reach local database"))
        }
    }
}