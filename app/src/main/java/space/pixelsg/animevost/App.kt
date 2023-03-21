package space.pixelsg.animevost

import android.app.Application
import com.google.android.material.color.DynamicColors
import space.pixelsg.animevost.di.AppComponent
import space.pixelsg.animevost.di.AppModule
import space.pixelsg.animevost.di.DaggerAppComponent
import space.pixelsg.core.db.di.DatabaseModule
import space.pixelsg.core.di.CoreComponent
import space.pixelsg.core.di.DaggerCoreComponent

class App : Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
            .databaseModule(DatabaseModule(this))
            .build()
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .coreComponent(coreComponent)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}