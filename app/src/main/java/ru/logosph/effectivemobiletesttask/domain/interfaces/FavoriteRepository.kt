package ru.logosph.effectivemobiletesttask.domain.interfaces

import ru.logosph.effectivemobiletesttask.domain.models.FavoriteEntity

interface FavoriteRepository {

    suspend fun getAll(): List<FavoriteEntity>
    suspend fun insert(favoriteEntity: FavoriteEntity)
    suspend fun delete(favoriteEntity: FavoriteEntity)


}