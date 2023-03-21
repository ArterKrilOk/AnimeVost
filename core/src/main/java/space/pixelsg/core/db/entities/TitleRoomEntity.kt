package space.pixelsg.core.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity("titles")
data class TitleRoomEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    val genres: String,
    @ColumnInfo
    val year: String,
    @ColumnInfo
    val poster: String,
    @ColumnInfo
    val screenshots: String,
    @ColumnInfo
    val rating: Int,
    @ColumnInfo
    val votes: Int,
    @ColumnInfo
    val timer: Long,
    @ColumnInfo
    val type: String,
    @ColumnInfo
    val director: String,
    @ColumnInfo
    val colors: String,
    @ColumnInfo
    val updateDate: Long = Date().time
)
