package com.example.pokedex.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.common.PokeSort
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.databinding.ActivityHomeBinding
import com.example.pokedex.presentation.UiEvent
import com.example.pokedex.presentation.ui.adapter.PokemonAdapter
import com.example.pokedex.presentation.ui.adapter.SpaceItemDecoration
import com.example.pokedex.presentation.ui.base.BaseActivity
import com.example.pokedex.presentation.ui.widget.SearchEditTextView
import com.example.pokedex.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { PokemonAdapter() }

    private var sortBy: PokeSort? = null
    private var queryString: String? = null
    private val listPoke: ArrayList<PokemonEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPokemonList()

        initRecyclerView()
        doQueryChange()

        binding.btnSort.onShortingChangeListener {
            sortBy = it
            viewModel.getPokemonList(queryString?: "", it)
        }

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

    private fun doQueryChange() {
        binding.etSearch.setOnSearchTextListener(object : SearchEditTextView.SearchTextListener{
            override fun onQueryString(query: String) {
                queryString = query
                viewModel.getPokemonList(query, sortBy ?: PokeSort.NUMBER)
            }

            override fun onCloseButton() {
                viewModel.getPokemonList()
            }
        })
    }

    private fun initRecyclerView(){
        with(binding.rvListPoke) {
            adapter = this@HomeActivity.adapter
            layoutManager = GridLayoutManager(this@HomeActivity, SPAN_COUNT)
            setHasFixedSize(true)
            addItemDecoration(SpaceItemDecoration(SPAN_COUNT, SPACING, false))
        }

        adapter.setOnClickListener{ pos ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.EXTRA_POSITION, pos)
            }.apply { startActivity(this) }
        }
    }

    private fun renderList(items: List<PokemonEntity>) {
        listPoke?.addAll(items)
        adapter.submitList(items)
    }

    private companion object{
        const val SPAN_COUNT = 3
        const val SPACING = 24
    }

}