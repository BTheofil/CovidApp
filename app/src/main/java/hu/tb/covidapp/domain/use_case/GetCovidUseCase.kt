package hu.tb.covidapp.domain.use_case

import hu.tb.covidapp.common.Resource
import hu.tb.covidapp.data.remote.dto.toSelectedCountry
import hu.tb.covidapp.domain.model.SelectedCountry
import hu.tb.covidapp.domain.repository.CovidRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCovidUseCase @Inject constructor(private val repository: CovidRepository) {

    operator fun invoke(countryName: String) : Flow<Resource<List<SelectedCountry>>> = flow {
        try {
            emit(Resource.Loading())
            val selectedCountries = repository.getSelectedCountry(countryName).map { it.toSelectedCountry() }
            emit(Resource.Success(selectedCountries))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Could not reach server. Check your internet connection"))
        }
    }
}