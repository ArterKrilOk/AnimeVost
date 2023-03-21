package space.pixelsg.animevosttv.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class TvAppModule(private val context: Context) {
    @Provides
    @TvAppScope
    fun provideContext(): Context = context
}