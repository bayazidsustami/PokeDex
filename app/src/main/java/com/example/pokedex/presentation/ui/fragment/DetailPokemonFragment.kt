package com.example.pokedex.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.databinding.FragmentDetailBinding
import com.example.pokedex.presentation.loadImage
import com.example.pokedex.presentation.ui.base.BaseFragment

class DetailPokemonFragment: BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val data = arguments?.getParcelable<PokemonEntity>(EXTRA_DATA)

        data?.let {
            with(binding) {
                ivPokemon.loadImage(it.imageUrl)
                tvPokeName.text = it.pokeName
                tvPokeNumber.text = it.pokeNumber
            }
        }

        binding.ivBack.setOnClickListener {
            activity?.finish()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    companion object{
        const val EXTRA_DATA= "poke_data"
    }
}