package space.pixelsg.core.network.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.pixelsg.core.di.CoreScope
import space.pixelsg.core.network.api.AnimeVostApiV1
import javax.inject.Named

@Module
class NetworkModule {
    @Provides
    @Named("api_url")
    fun provideApiUrl(): String = "https://api.animevost.org/"

    @Provides
    @CoreScope
    fun provideGson(): Gson = Gson()

    @Provides
    @CoreScope
    fun provideRetrofit(gson: Gson, @Named("api_url") url: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @CoreScope
    fun provideApi(retrofit: Retrofit): AnimeVostApiV1 = retrofit.create(AnimeVostApiV1::class.java)
}