package hu.tb.covidapp.data.local.repository

import androidx.lifecycle.LiveData
import hu.tb.covidapp.data.local.CountryDao
import hu.tb.covidapp.data.local.entity.CountryEntity
import hu.tb.covidapp.domain.repository.CountryEntityRepository

class CountryEntityImpl(private val dao: CountryDao) : CountryEntityRepository{
    override fun getCounties(): LiveData<List<CountryEntity>> {
        return dao.getCountries()
    }

    override suspend fun getCountryById(id: Int): CountryEntity? {
        return dao.getCountryById(id)
    }

    override suspend fun insertCountry(countryEntity: CountryEntity) {
        dao.insertCountry(countryEntity)
    }

    override suspend fun deleteCountry(countryEntity: CountryEntity) {
        dao.deleteCountry(countryEntity)
    }
}