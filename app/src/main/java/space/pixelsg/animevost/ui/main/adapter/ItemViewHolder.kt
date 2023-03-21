package space.pixelsg.animevost.ui.main.adapter

import com.bumptech.glide.Glide
import space.pixelsg.animevost.common.adapter.BaseViewHolder
import space.pixelsg.animevost.databinding.TitleItemBinding

class ItemViewHolder(binding: TitleItemBinding) : BaseViewHolder<Item, TitleItemBinding>(binding) {
    override fun bindItem(model: Item) {
        Glide.with(binding.posterView)
            .load(model.posterUrl)
            .centerCrop()
            .into(binding.posterView)
        binding.posterView.contentDescription = model.title
        binding.titleView.text = model.title
    }
}