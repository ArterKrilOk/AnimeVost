package space.pixelsg.core.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import space.pixelsg.core.db.entities.EpisodeRoomEntity

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: EpisodeRoomEntity): Long

    @Update
    suspend fun update(entity: EpisodeRoomEntity)

    @Query("SELECT * FROM episodes WHERE id=:id")
    suspend fun getEpisode(id: Long): EpisodeRoomEntity?

    @Query("SELECT * FROM episodes WHERE titleID=:titleID")
    suspend fun getEpisodesByTitle(titleID: Long): List<EpisodeRoomEntity>
}