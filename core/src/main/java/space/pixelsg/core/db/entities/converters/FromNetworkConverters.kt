package space.pixelsg.core.db.entities.converters

import space.pixelsg.core.db.entities.EpisodeRoomEntity
import space.pixelsg.core.db.entities.TitleRoomEntity
import space.pixelsg.core.network.models.NetworkEpisode
import space.pixelsg.core.network.models.NetworkTitle

object FromNetworkConverters {
    fun NetworkTitle.toDbTitle(): TitleRoomEntity = TitleRoomEntity(
        id = id,
        title = title,
        description = description,
        genres = genre,
        year = year,
        poster = urlImagePreview,
        screenshots = screenImage.joinToString(","),
        rating = rating,
        votes = votes,
        timer = timer.toLongOrNull() ?: 0,
        type = type,
        director = director,
        colors = ""
    )

    fun NetworkTitle.toDbEpisodes(): List<EpisodeRoomEntity> = getSeriePairs().map {
        EpisodeRoomEntity(
            it.second.toLong(),
            id,
            it.first,
            "",
            "",
            "",
            0,
            0
        )
    }

    fun EpisodeRoomEntity.extendWithNetworkEpisode(episode: NetworkEpisode) = copy(
        preview = episode.preview,
        hd = episode.hd,
        sd = episode.std
    )
}