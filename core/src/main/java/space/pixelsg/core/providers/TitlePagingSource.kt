package space.pixelsg.core.providers

import androidx.paging.PagingSource
import androidx.paging.PagingState
import space.pixelsg.core.models.Title

class TitlePagingSource(
    private val titleProvider: TitleProvider,
    private val query: String,
    private val limit: Int = 20
) : PagingSource<Int, Title>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Title> {
        return try {
            val page = params.key ?: 1
            val data = titleProvider.getLastTitles(
                TitleRequest(page, query, limit)
            ).data

            LoadResult.Page(
                data = data,
                prevKey = if (page - 1 <= 0) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Title>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}