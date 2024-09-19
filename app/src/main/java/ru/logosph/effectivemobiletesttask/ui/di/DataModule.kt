package ru.logosph.effectivemobiletesttask.ui.di

import androidx.room.Room
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.logosph.effectivemobiletesttask.data.VacanciesApiImpl
import ru.logosph.effectivemobiletesttask.data.favorite.FavoriteDB
import ru.logosph.effectivemobiletesttask.data.favorite.FavoriteDao
import ru.logosph.effectivemobiletesttask.data.favorite.FavoriteRepositoryImpl
import ru.logosph.effectivemobiletesttask.domain.interfaces.FavoriteRepository
import ru.logosph.effectivemobiletesttask.domain.interfaces.VacanciesApi

val dataModule = module {

    single<OkHttpClient> { OkHttpClient() }
    single<VacanciesApi> { VacanciesApiImpl(client = get()) }

    single<FavoriteDB> {
        Room.databaseBuilder(
            androidContext(),
            FavoriteDB::class.java,
            "favorite_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    factory<FavoriteDao> {
        get<FavoriteDB>().favoriteDao()
    }
    single<FavoriteRepository> {
        FavoriteRepositoryImpl(favoriteDao = get())
    }


}