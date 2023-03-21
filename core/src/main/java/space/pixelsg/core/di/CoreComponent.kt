package space.pixelsg.core.di

import dagger.Component
import space.pixelsg.core.db.di.DatabaseModule
import space.pixelsg.core.network.di.NetworkModule
import space.pixelsg.core.providers.EpisodesProvider
import space.pixelsg.core.providers.TitleProvider

@CoreScope
@Component(
    modules = [
        CoreModule::class,
        DatabaseModule::class,
        NetworkModule::class
    ]
)
interface CoreComponent {
    val titleProvider: TitleProvider
    val episodesProvider: EpisodesProvider
}