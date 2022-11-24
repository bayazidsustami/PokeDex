package com.example.pokedex.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.databinding.FragmentDetailBinding
import com.example.pokedex.presentation.loadImage
import com.example.pokedex.presentation.ui.base.BaseFragment

class DetailPokemonFragment: BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getParcelable<PokemonEntity>(EXTRA_DATA)

        data?.let {
            binding.ivPokemon.loadImage(it.imageUrl)
            binding.tvPokeName.text = it.pokeName
            binding.tvPokeNumber.text = it.pokeNumber
        }

    }

    companion object{
        const val EXTRA_DATA= "poke_data"
    }
}