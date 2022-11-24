package com.example.pokedex.presentation.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.pokedex.databinding.ActivityDetailBinding
import com.example.pokedex.presentation.UiEvent
import com.example.pokedex.presentation.gone
import com.example.pokedex.presentation.ui.adapter.SectionAdapter
import com.example.pokedex.presentation.ui.base.BaseActivity
import com.example.pokedex.presentation.viewmodel.HomeViewModel
import com.example.pokedex.presentation.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    private var lastPosition = 0
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPokemonList()

        val position = intent.getIntExtra(EXTRA_POSITION, 0)

        viewModel.pokeList.observe(this) {
            if (it is UiEvent.Success) {
                val adapter = SectionAdapter(this, it.data)
                lastPosition = it.data.size - 1
                binding.vpDetails.adapter = adapter
                binding.vpDetails.currentItem = position
            }
        }

        binding.vpDetails.registerOnPageChangeCallback(pageChangeCallback)

        binding.ivArrowRight.setOnClickListener {
            currentPage += 1
            binding.vpDetails.setCurrentItem(currentPage, true)
        }

        binding.ivArrowLeft.setOnClickListener {
            currentPage -= 1
            binding.vpDetails.setCurrentItem(currentPage, true)
        }
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        override fun onPageSelected(position: Int) {
            currentPage = position

            if (position == 0) {
                binding.ivArrowLeft.gone()
            } else {
                binding.ivArrowLeft.visible()
            }

            if (position == lastPosition) {
                binding.ivArrowRight.gone()
            } else {
                binding.ivArrowRight.visible()
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    override fun onStop() {
        super.onStop()
        binding.vpDetails.unregisterOnPageChangeCallback(pageChangeCallback)
    }

    companion object {
        const val EXTRA_POSITION = "extra_position"
    }

}