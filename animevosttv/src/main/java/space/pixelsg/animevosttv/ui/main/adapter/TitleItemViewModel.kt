package space.pixelsg.animevosttv.ui.main.adapter

import com.bumptech.glide.Glide
import space.pixelsg.animevosttv.adapter.BaseViewHolder
import space.pixelsg.animevosttv.databinding.TitleItemBinding

class TitleItemViewModel(binding: TitleItemBinding, private val focusedListener: (Item) -> Unit) :
    BaseViewHolder<Item, TitleItemBinding>(binding) {
    override fun bindItem(model: Item) {
        binding.posterView.contentDescription = model.title
        Glide.with(binding.posterView)
            .load(model.poster)
            .centerCrop()
            .into(binding.posterView)
        binding.root.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) focusedListener(model)
        }
    }
}