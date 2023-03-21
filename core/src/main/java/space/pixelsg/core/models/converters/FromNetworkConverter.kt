package space.pixelsg.core.models.converters

import space.pixelsg.core.models.Title
import space.pixelsg.core.network.models.NetworkTitle

object FromNetworkConverter {
    fun NetworkTitle.toCoreTitle(): Title = Title(
        id = id,
        title = title,
        description = description,
        year = year,
        genres = genre,
        posterUrl = urlImagePreview,
        rating = rating,
        votesCount = votes,
        episodesCount = getSeriePairs().size,
        screenshots = emptyList()
    )
}