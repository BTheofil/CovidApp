package hu.tb.covidapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.tb.covidapp.data.local.entity.CountryEntity

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: CountryEntity)

    @Delete
    suspend fun deleteCountry(country: CountryEntity)

    @Query("SELECT * FROM countryEntity")
    fun getCountries(): LiveData<List<CountryEntity>>

    @Query("SELECT * FROM countryEntity WHERE id = :id")
    suspend fun getCountryById(id: Int): CountryEntity?
}