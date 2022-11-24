package com.example.pokedex.presentation.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.data.datasource.remote.response.Pokemon
import com.example.pokedex.databinding.ActivityHomeBinding
import com.example.pokedex.presentation.UiEvent
import com.example.pokedex.presentation.ui.adapter.PokemonAdapter
import com.example.pokedex.presentation.ui.adapter.SpaceItemDecoration
import com.example.pokedex.presentation.ui.base.BaseActivity
import com.example.pokedex.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { PokemonAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPokemonList()

        viewModel.pokeList.observe(this){
            when(it) {
                is UiEvent.Loading -> {}
                is UiEvent.Success -> {
                    renderList(it.data)
                }
                is UiEvent.Error -> {}
            }
        }
    }

    private fun renderList(items: List<Pokemon>) {
        with(binding.rvListPoke) {
            adapter = this@HomeActivity.adapter
            layoutManager = GridLayoutManager(this@HomeActivity, 3)
            setHasFixedSize(true)
            addItemDecoration(SpaceItemDecoration(3, 24, false))
        }
        adapter.submitList(items)
    }

}