package hu.tb.covidapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CountryDatabase::class],
    version = 1
)
abstract class CountryDatabase : RoomDatabase() {

    abstract val dao: CountryDao
}