package space.pixelsg.animevosttv.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import space.pixelsg.animevosttv.adapter.BasePagingAdapter
import space.pixelsg.animevosttv.databinding.TitleItemBinding

class ItemPagingAdapter(
    private val focusListener: (Item) -> Unit
) : BasePagingAdapter<Item, TitleItemViewModel>(Item.DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TitleItemViewModel(
        TitleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        focusListener
    )
}