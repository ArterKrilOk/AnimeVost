package space.pixelsg.core.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import space.pixelsg.core.db.entities.TitleRoomEntity
import space.pixelsg.core.db.entities.TitleWithEpisodes

@Dao
interface TitleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: TitleRoomEntity): Long

    @Update
    suspend fun update(entity: TitleRoomEntity)

    @Query("SELECT * FROM titles WHERE id=:id")
    suspend fun getTitle(id: Long): TitleRoomEntity?

    @Query("SELECT * FROM titles WHERE id IN (:ids) ORDER BY updateDate DESC")
    @Transaction
    suspend fun getTitlesByIds(ids: List<Long>): List<TitleWithEpisodes>

    @Query("SELECT * FROM titles WHERE id=:id")
    fun getTitleFlow(id: Long): Flow<TitleRoomEntity?>

    @Query("SELECT * FROM titles WHERE id=:id")
    @Transaction
    suspend fun getTitleWithEpisodes(id: Long): TitleWithEpisodes?

    @Query("SELECT * FROM titles WHERE id=:id")
    @Transaction
    fun getTitleWithEpisodesFlow(id: Long): Flow<TitleWithEpisodes?>

    @Query("SELECT * FROM titles ORDER BY updateDate DESC LIMIT :limit")
    @Transaction
    suspend fun getLastTitles(limit: Int): List<TitleWithEpisodes>
}