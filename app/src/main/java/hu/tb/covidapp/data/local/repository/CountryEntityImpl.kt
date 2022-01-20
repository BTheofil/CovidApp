package hu.tb.covidapp.data.local.repository

import android.util.Log
import hu.tb.covidapp.data.local.CountryDao
import hu.tb.covidapp.data.local.entity.CountryEntity
import hu.tb.covidapp.domain.repository.CountryEntityRepository
import kotlinx.coroutines.flow.Flow

class CountryEntityImpl(private val dao: CountryDao) : CountryEntityRepository{
    override fun getCounties(): Flow<List<CountryEntity>> {
        return dao.getCountries()
    }

    override suspend fun getCountryById(id: Int): CountryEntity? {
        return dao.getCountryById(id)
    }

    override suspend fun insertCountry(countryEntity: CountryEntity) {
        Log.d("HERE: ", "Impl")
        dao.insertCountry(countryEntity)
    }

    override suspend fun deleteCountry(countryEntity: CountryEntity) {
        dao.deleteCountry(countryEntity)
    }
}