package space.pixelsg.animevosttv.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import space.pixelsg.animevosttv.app.TvApp
import space.pixelsg.animevosttv.ui.main.adapter.Item
import space.pixelsg.core.models.Title
import space.pixelsg.core.providers.TitlePagingSource

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val app: TvApp by lazy { application as TvApp }
    private val titleProvider = app.appComponent.titleProvider

    val selectedTitle = MutableStateFlow<Title?>(null)

    fun setSelected(titleID: Long) = viewModelScope.launch {
        try {
            selectedTitle.emit(titleProvider.getTitle(titleID))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    val items = Pager(PagingConfig(20, initialLoadSize = 20, enablePlaceholders = false)) {
        TitlePagingSource(titleProvider)
    }.flow.cachedIn(viewModelScope).map { pagingData ->
        pagingData.map { Item(it.id, it.simpleName, it.posterUrl) }
    }.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(replayExpirationMillis = Long.MAX_VALUE),
        1
    )
}