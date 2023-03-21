package space.pixelsg.core.providers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import space.pixelsg.core.db.dao.EpisodeDao
import space.pixelsg.core.db.dao.PageMappingDao
import space.pixelsg.core.db.dao.TitleDao
import space.pixelsg.core.db.entities.PageMapping
import space.pixelsg.core.db.entities.converters.FromNetworkConverters.toDbEpisodes
import space.pixelsg.core.db.entities.converters.FromNetworkConverters.toDbTitle
import space.pixelsg.core.di.CoreScope
import space.pixelsg.core.models.Paged
import space.pixelsg.core.models.converters.FromDatabaseConverter.toCoreTitle
import space.pixelsg.core.models.converters.FromNetworkConverter.toCoreTitle
import space.pixelsg.core.network.api.AnimeVostApiV1
import space.pixelsg.core.network.api.Api
import javax.inject.Inject

@CoreScope
class TitleProvider @Inject constructor(
    private val api: AnimeVostApiV1,
    private val titleDao: TitleDao,
    private val episodeDao: EpisodeDao,
    private val pageMappingDao: PageMappingDao
) {
    suspend fun getLastTitles(request: TitleRequest) = withContext(Dispatchers.IO) {
        //Load first from network and check updates
        if (request.page == 1) getLastTitlesFromNetwork(request)
        else pageMappingDao.getPageMapping(request.page, request.params).let { mapping ->
            if (mapping == null) getLastTitlesFromNetwork(request)
            else titleDao.getTitlesByIds(mapping.ids()).map { it.toCoreTitle() }.let {
                Paged(it.size, request.page, it)
            }
        }
    }

    suspend fun getTitle(titleID: Long) = withContext(Dispatchers.IO) {
        val episodes = episodeDao.getEpisodesByTitle(titleID)
        titleDao.getTitleWithEpisodes(titleID)?.toCoreTitle().let { title ->
            title?.copy(screenshots = episodes.map { it.preview }.filter { it.isNotEmpty() })
        } ?: throw RuntimeException("Title not found")
    }

    suspend fun getLastTitlesFromNetwork(request: TitleRequest) =
        Api.apiCall {
            if (request.query.isEmpty()) api.getLastUpdates(request.page, request.limit)
            else api.getAnimeByName(request.query)
        }.also { response ->
            //Insert or update Titles
            response.data.map { it.toDbTitle() }.forEach {
                titleDao.upsert(it)
            }
            //Insert new Episodes
            response.data.map { it.toDbEpisodes() }.forEach { episodes ->
                episodes.forEach { episodeDao.insert(it) }
            }
            //Save page mapping
            val receivedIds = response.data.map { it.id }
            val storedMapping = pageMappingDao.getPageMapping(request.page, request.params)
            if (storedMapping != null && !receivedIds.compare(storedMapping.ids())) {
                pageMappingDao.clear()
            }
            pageMappingDao.upsert(
                PageMapping(
                    page = request.page,
                    params = request.params,
                    results = receivedIds.joinToString(",")
                )
            )
        }.run {
            Paged(state.count, request.page, data.map { it.toCoreTitle() })
        }

    private fun <T> List<T>.compare(list: List<T>): Boolean =
        if (size != list.size) false
        else zip(list).all { it.first == it.second }
}

data class TitleRequest(
    val page: Int,
    val query: String = "",
    val limit: Int = 20
) {
    val params = "$limit|$query"
}