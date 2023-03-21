package space.pixelsg.animevost.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.*
import space.pixelsg.animevost.App
import space.pixelsg.animevost.ui.main.adapter.Item
import space.pixelsg.core.providers.TitlePagingSource

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val app: App by lazy { application as App }

    private val titleProvider = app.appComponent.titleProvider

    val query = MutableStateFlow("")

    val items = query.flatMapConcat { query ->
        Pager(PagingConfig(20, initialLoadSize = 20, enablePlaceholders = false)) {
            TitlePagingSource(titleProvider, query)
        }.flow.cachedIn(viewModelScope)
    }.map { pagingData ->
        pagingData.map { Item(it.id, it.simpleName, it.posterUrl) }
    }.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(replayExpirationMillis = Long.MAX_VALUE),
        1
    )
}