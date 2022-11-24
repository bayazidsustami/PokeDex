package com.example.pokedex.presentation.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.example.pokedex.databinding.ActivityHomeBinding
import com.example.pokedex.presentation.ui.base.BaseActivity
import com.example.pokedex.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPokemonList()

        viewModel.pokeList.observe(this){

        }
    }

}