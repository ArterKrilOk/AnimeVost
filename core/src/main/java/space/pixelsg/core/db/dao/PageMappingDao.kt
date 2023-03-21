package space.pixelsg.core.db.dao

import androidx.room.*
import space.pixelsg.core.db.entities.PageMapping

@Dao
interface PageMappingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: PageMapping)

    @Query("SELECT * FROM page_mapping WHERE (page=:page) AND (params=:params)")
    suspend fun getPageMapping(page: Int, params: String = ""): PageMapping?

    @Query("DELETE FROM page_mapping")
    suspend fun clear()
}