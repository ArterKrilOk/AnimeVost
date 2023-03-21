package space.pixelsg.animevosttv.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.*

class AnimatedImageView : AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private lateinit var job: Job
    var urls: List<String> = listOf()
        set(value) {
            field = value
            start()
        }

    fun start() {
        if (urls.isEmpty()) return

        stop()
        job = findViewTreeLifecycleOwner()?.lifecycleScope?.launch { run() } ?: return
    }

    private suspend fun run() {
        var index = 0
        withContext(Dispatchers.Default) {
            while (urls.isNotEmpty()) {
                index++
                index %= urls.size
                Glide.with(this@AnimatedImageView).load(urls[index]).preload()
                delay(5000)
                findViewTreeLifecycleOwner()?.lifecycleScope?.launch {
                    Glide
                        .with(this@AnimatedImageView)
                        .load(urls[index])
                        .into(this@AnimatedImageView)
                }
            }
        }
    }

    fun stop() {
        if (this::job.isInitialized) job.cancel()
    }
}