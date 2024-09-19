package ru.logosph.effectivemobiletesttask

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.logosph.effectivemobiletesttask.ui.di.appModule
import ru.logosph.effectivemobiletesttask.ui.di.dataModule
import ru.logosph.effectivemobiletesttask.ui.di.domainModule

class App : Application() {

    override fun onCreate() {

        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                domainModule,
                dataModule
            )
        }

        super.onCreate()
    }
}