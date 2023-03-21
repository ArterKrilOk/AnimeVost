package space.pixelsg.core.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import space.pixelsg.core.db.dao.EpisodeDao
import space.pixelsg.core.db.dao.PageMappingDao
import space.pixelsg.core.db.dao.TitleDao
import space.pixelsg.core.db.entities.EpisodeRoomEntity
import space.pixelsg.core.db.entities.PageMapping
import space.pixelsg.core.db.entities.TitleRoomEntity

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        TitleRoomEntity::class,
        EpisodeRoomEntity::class,
        PageMapping::class
    ]
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getEpisodeDao(): EpisodeDao
    abstract fun getTitleDao(): TitleDao
    abstract fun getPageMappingDao(): PageMappingDao
}