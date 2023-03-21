package space.pixelsg.animevosttv.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil


data class Item(
    val id: Long,
    val title: String,
    val poster: String
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item) =
                oldItem == newItem
        }
    }
}