package ru.cityproblemsmap

import android.app.Application
import org.koin.android.ext.android.startKoin
import ru.cityproblemsmap.di.modules

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules)
    }

}