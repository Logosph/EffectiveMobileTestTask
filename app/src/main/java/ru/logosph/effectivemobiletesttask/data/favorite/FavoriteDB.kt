package ru.logosph.effectivemobiletesttask.data.favorite

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.logosph.effectivemobiletesttask.domain.models.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
@TypeConverters(AddressConverter::class, ExperienceConverter::class, ListStringConverter::class, SalaryConverter::class)
abstract class FavoriteDB : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}