package hu.tb.covidapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.tb.covidapp.data.local.entity.CountryEntity

@Database(
    entities = [CountryEntity::class],
    version = 1
)
abstract class CountryDatabase : RoomDatabase() {

    abstract val dao: CountryDao
}