package ru.logosph.effectivemobiletesttask.data.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.logosph.effectivemobiletesttask.domain.models.FavoriteEntity

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_table")
    fun getAll(): List<FavoriteEntity>

    @Insert
    fun insert(favoriteEntity: FavoriteEntity)

    @Delete
    fun delete(favoriteEntity: FavoriteEntity)
}