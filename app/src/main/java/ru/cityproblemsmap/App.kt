package ru.cityproblemsmap

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.android.startKoin
import ru.cityproblemsmap.di.modules

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // Koin
        startKoin(this, modules)

        // Yandex Maps
        MapKitFactory.setApiKey(Config.YANDEX_MAPS_SDK_KEY)
        MapKitFactory.initialize(this)
    }

}