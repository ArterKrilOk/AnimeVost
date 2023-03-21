package space.pixelsg.animevosttv.di

import dagger.Component
import space.pixelsg.core.di.CoreComponent
import space.pixelsg.core.providers.EpisodesProvider
import space.pixelsg.core.providers.TitleProvider

@TvAppScope
@Component(
    modules = [
        TvAppModule::class
    ],
    dependencies = [
        CoreComponent::class
    ]
)
interface TvAppComponent {
    val titleProvider: TitleProvider
    val episodesProvider: EpisodesProvider
}