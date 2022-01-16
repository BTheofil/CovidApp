package hu.tb.covidapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.tb.covidapp.domain.model.Country

@Entity
data class CountryEntity(
    @PrimaryKey val id: Int? = null,
    val country: String,
    val confirmed: Int,
    val critical: Int,
    val deaths: Int,
    val recovered: Int
)

fun CountryEntity.toCountry(): Country {
    return Country(
        country = country,
        confirmed = confirmed,
        critical = critical,
        deaths = deaths,
        recovered = recovered
    )
}

