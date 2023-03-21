package space.pixelsg.core.providers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import space.pixelsg.core.db.dao.EpisodeDao
import space.pixelsg.core.models.converters.FromDatabaseConverter.toEpisode
import space.pixelsg.core.network.api.AnimeVostApiV1
import space.pixelsg.core.network.api.Api
import javax.inject.Inject

class EpisodesProvider @Inject constructor(
    private val api: AnimeVostApiV1,
    private val episodeDao: EpisodeDao,
) {
    suspend fun getEpisodes(titleID: Long) = withContext(Dispatchers.IO) {
        val episodes = episodeDao.getEpisodesByTitle(titleID)
        if (episodes.any { it.sd.isEmpty() }) {
            //If some is empty, load and update episodes
            val remoteEpisodes = Api.apiCall { api.getEpisodes(titleID) }
            remoteEpisodes.forEach { nEpisode ->
                episodes.firstOrNull { it.name == nEpisode.name }?.let { episode ->
                    episodeDao.update(
                        episode.copy(
                            hd = nEpisode.hd,
                            sd = nEpisode.std,
                            preview = nEpisode.preview
                        )
                    )
                }
            }
            episodeDao.getEpisodesByTitle(titleID).map { it.toEpisode() }
        } else episodes.map { it.toEpisode() }
    }
}