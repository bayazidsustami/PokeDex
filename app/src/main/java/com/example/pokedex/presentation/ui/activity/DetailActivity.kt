package com.example.pokedex.presentation.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.example.pokedex.databinding.ActivityDetailBinding
import com.example.pokedex.presentation.UiEvent
import com.example.pokedex.presentation.ui.adapter.SectionAdapter
import com.example.pokedex.presentation.ui.base.BaseActivity
import com.example.pokedex.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPokemonList()

        val position = intent.getIntExtra(EXTRA_POSITION, 0)

        viewModel.pokeList.observe(this) {
            if (it is UiEvent.Success) {
                val adapter = SectionAdapter(this, it.data)
                binding.vpDetails.adapter = adapter
                binding.vpDetails.currentItem = position
            }
        }
    }

    companion object {
        const val EXTRA_POSITION = "extra_position"
    }

}