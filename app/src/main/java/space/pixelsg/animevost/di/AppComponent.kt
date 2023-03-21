package space.pixelsg.animevost.di

import dagger.Component
import space.pixelsg.core.di.CoreComponent
import space.pixelsg.core.di.CoreModule
import space.pixelsg.core.providers.EpisodesProvider
import space.pixelsg.core.providers.TitleProvider
import javax.inject.Singleton

@AppScope
@Component(
    modules = [
        AppModule::class
    ],
    dependencies = [
        CoreComponent::class
    ]
)
interface AppComponent {
    val titleProvider: TitleProvider
    val episodesProvider: EpisodesProvider
}