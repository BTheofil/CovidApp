package hu.tb.covidapp.data.local

import androidx.room.*
import hu.tb.covidapp.data.local.entity.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: CountryEntity)

    @Delete
    suspend fun deleteCountry(country: CountryEntity)

    @Query("SELECT * FROM countryEntity")
    fun getCountries(): List<CountryEntity>

    @Query("SELECT * FROM countryEntity WHERE id = :id")
    suspend fun getCountryById(id: Int): CountryEntity?
}