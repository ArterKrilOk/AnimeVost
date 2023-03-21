package space.pixelsg.core.db.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import space.pixelsg.core.db.dao.EpisodeDao
import space.pixelsg.core.db.dao.PageMappingDao
import space.pixelsg.core.db.dao.TitleDao
import space.pixelsg.core.db.database.AppDatabase
import space.pixelsg.core.di.CoreScope

@Module
class DatabaseModule(private val context: Context) {
    @Provides
    @CoreScope
    fun provideDatabase(): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app_database.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @CoreScope
    fun provideEpisodeDao(database: AppDatabase): EpisodeDao = database.getEpisodeDao()

    @Provides
    @CoreScope
    fun provideTitleDao(database: AppDatabase): TitleDao = database.getTitleDao()

    @Provides
    @CoreScope
    fun providePageMappingDao(database: AppDatabase): PageMappingDao = database.getPageMappingDao()
}