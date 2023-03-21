package space.pixelsg.core.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page_mapping")
data class PageMapping(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    val params: String = "",
    @ColumnInfo
    val page: Int,
    @ColumnInfo
    val results: String,
    @ColumnInfo
    val createDate: Long = System.currentTimeMillis()
) {
    fun ids() = results.split(",").map { it.toLong() }

    fun isTooOld(): Boolean = System.currentTimeMillis() - createDate > 180000
}
