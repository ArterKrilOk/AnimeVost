package space.pixelsg.core.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("episodes")
data class EpisodeRoomEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo
    val titleID: Long,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val preview: String,
    @ColumnInfo
    val sd: String,
    @ColumnInfo
    val hd: String,
    @ColumnInfo
    val length: Long,
    @ColumnInfo
    val continueFrom: Long = 0
)
