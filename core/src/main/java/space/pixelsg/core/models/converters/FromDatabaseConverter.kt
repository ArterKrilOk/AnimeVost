package space.pixelsg.core.models.converters

import space.pixelsg.core.db.entities.EpisodeRoomEntity
import space.pixelsg.core.db.entities.TitleWithEpisodes
import space.pixelsg.core.models.Episode
import space.pixelsg.core.models.Title

object FromDatabaseConverter {
    fun TitleWithEpisodes.toCoreTitle(): Title = Title(
        id = id,
        title = title.title,
        description = title.description,
        year = title.year,
        genres = title.genres,
        posterUrl = title.poster,
        rating = title.rating,
        votesCount = title.votes,
        episodesCount = episodes.size,
        screenshots = emptyList()
    )

    fun EpisodeRoomEntity.toEpisode(): Episode = Episode(
        id, titleID, name, sd, hd, preview
    )
}