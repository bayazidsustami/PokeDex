package com.example.pokedex.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.databinding.FragmentDetailBinding
import com.example.pokedex.presentation.getColorRes
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

            setColors(it.colorTypes)
        }

        binding.ivBack.setOnClickListener {
            activity?.finish()
        }

    }

    private fun setColors(colorTypes: String) {
        with(binding) {
            container.setBackgroundResource(getColorRes(colorTypes))
            tvAbout.setTextColor(colorTypes)
            tvBaseStats.setTextColor(colorTypes)
            viewStats.tvHp.setTextColor(colorTypes)
            viewStats.tvAtk.setTextColor(colorTypes)
            viewStats.tvDef.setTextColor(colorTypes)
            viewStats.tvSatk.setTextColor(colorTypes)
            viewStats.tvSdef.setTextColor(colorTypes)
            viewStats.tvSpd.setTextColor(colorTypes)
        }
    }

    private fun TextView.setTextColor(colorTypes: String) {
        setTextColor(ContextCompat.getColor(requireContext(), getColorRes(colorTypes)))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    companion object{
        const val EXTRA_DATA= "poke_data"
    }
}