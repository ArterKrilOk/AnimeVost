package space.pixelsg.core.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TitleWithEpisodes(
    @Embedded
    val title: TitleRoomEntity,
    @Relation(parentColumn = "id", entityColumn = "titleID", entity = EpisodeRoomEntity::class)
    val episodes: List<EpisodeRoomEntity>
) {
    val id: Long
        get() = title.id
}
