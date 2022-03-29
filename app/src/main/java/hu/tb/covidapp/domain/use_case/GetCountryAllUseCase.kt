package hu.tb.covidapp.domain.use_case

import hu.tb.covidapp.common.Resource
import hu.tb.covidapp.data.remote.dto.toCountry
import hu.tb.covidapp.domain.model.Country
import hu.tb.covidapp.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCountryAllUseCase @Inject constructor(private val repository: CountryRepository) {

    operator fun invoke() : Flow<Resource<List<Country>>> = flow {
        try {
            emit(Resource.Loading<List<Country>>())
            val countries = repository.getCountryAll().map { it.toCountry() }
            emit(Resource.Success<List<Country>>(countries))
        } catch (e: HttpException){
            emit(Resource.Error<List<Country>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Country>>("Could not reach server. Check your internet connection"))
        }
    }
}