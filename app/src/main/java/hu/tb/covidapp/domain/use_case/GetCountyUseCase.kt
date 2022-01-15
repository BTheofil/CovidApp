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

class GetCountyUseCase @Inject constructor(private val repository: CountryRepository) {

    operator fun invoke(countryName: String) : Flow<Resource<Country>> = flow {
        try {
            emit(Resource.Loading())
            val selectedCountry = repository.getCountryByName(countryName).toCountry()
            emit(Resource.Success(selectedCountry))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Could not reach server. Check your internet connection"))
        }
    }
}