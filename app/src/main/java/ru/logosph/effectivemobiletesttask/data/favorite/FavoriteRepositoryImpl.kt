package ru.logosph.effectivemobiletesttask.data.favorite

import ru.logosph.effectivemobiletesttask.domain.interfaces.FavoriteRepository
import ru.logosph.effectivemobiletesttask.domain.models.FavoriteEntity

class FavoriteRepositoryImpl(val favoriteDao: FavoriteDao) : FavoriteRepository {
    override suspend fun getAll() = favoriteDao.getAll()

    override suspend fun insert(favoriteEntity: FavoriteEntity) {
        favoriteDao.insert(favoriteEntity)
    }

    override suspend fun delete(favoriteEntity: FavoriteEntity) {
        favoriteDao.delete(favoriteEntity)
    }
}