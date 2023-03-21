package space.pixelsg.animevost.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import space.pixelsg.animevost.common.adapter.BasePagingAdapter
import space.pixelsg.animevost.databinding.TitleItemBinding

class ItemViewAdapter : BasePagingAdapter<Item, ItemViewHolder>(Item.DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        TitleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
}