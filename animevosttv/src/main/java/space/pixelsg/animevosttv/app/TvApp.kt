package space.pixelsg.animevosttv.app

import android.app.Application
import space.pixelsg.animevosttv.di.DaggerTvAppComponent
import space.pixelsg.animevosttv.di.TvAppComponent
import space.pixelsg.animevosttv.di.TvAppModule
import space.pixelsg.core.db.di.DatabaseModule
import space.pixelsg.core.di.CoreComponent
import space.pixelsg.core.di.DaggerCoreComponent

class TvApp : Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder().databaseModule(DatabaseModule(this)).build()
    }

    val appComponent: TvAppComponent =
        DaggerTvAppComponent
            .builder()
            .tvAppModule(TvAppModule(this))
            .coreComponent(coreComponent)
            .build()

}