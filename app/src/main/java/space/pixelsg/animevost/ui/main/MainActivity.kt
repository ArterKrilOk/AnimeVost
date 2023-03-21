package space.pixelsg.animevost.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import kotlinx.coroutines.flow.collectLatest
import space.pixelsg.animevost.databinding.ActivityMainBinding
import space.pixelsg.animevost.ui.UiUtils.onTextChanged
import space.pixelsg.animevost.ui.UiUtils.repeatOnCreated
import space.pixelsg.animevost.ui.main.adapter.ItemViewAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val itemAdapter by lazy { ItemViewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            adapter = itemAdapter
        }
        repeatOnCreated {
            viewModel.items.collectLatest {
                itemAdapter.submitData(it)
            }
        }
        repeatOnCreated {
            binding.searchEditText.onTextChanged(400).collect(viewModel.query)
        }
    }
}