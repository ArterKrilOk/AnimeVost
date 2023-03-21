package space.pixelsg.animevosttv.ui.main

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collectLatest
import space.pixelsg.animevosttv.R
import space.pixelsg.animevosttv.databinding.ActivityMainBinding
import space.pixelsg.animevosttv.ui.UiUtils.repeatOnCreated
import space.pixelsg.animevosttv.ui.main.adapter.ItemPagingAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val itemsAdapter by lazy {
        ItemPagingAdapter {
            viewModel.setSelected(it.id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            adapter = itemsAdapter
            offsetChildrenHorizontal(500)
            offsetLeftAndRight(500)
        }

        repeatOnCreated {
            viewModel.items.collectLatest {
                itemsAdapter.submitData(it)
            }
        }

        repeatOnCreated {
            viewModel.selectedTitle.collect {
                binding.contentPreview.isInvisible = it == null
                if (it == null) return@collect
                binding.bgImageView.glide(it.posterUrl)
                binding.posterView.glide(it.posterUrl)
                binding.bgImageView.urls = it.screenshots
                binding.titleView.text = it.simpleName
                binding.descrView.html(it.description)
                binding.infoView.text =
                    getString(R.string.title_info, it.rating.toString(), it.year, it.episodesCount)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        binding.bgImageView.stop()
    }

    override fun onResume() {
        super.onResume()
        binding.bgImageView.start()
    }

    private fun ImageView.glide(url: String) {
        Glide.with(this)
            .load(url)
            .centerCrop()
            .into(this)
    }

    private fun TextView.html(html: String) {
        text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(html)
        }
    }
}