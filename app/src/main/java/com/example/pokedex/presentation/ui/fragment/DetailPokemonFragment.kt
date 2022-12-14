package com.example.pokedex.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.R
import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.databinding.FragmentDetailBinding
import com.example.pokedex.presentation.*
import com.example.pokedex.presentation.ui.adapter.PokemonEvolutionAdapter
import com.example.pokedex.presentation.ui.base.BaseFragment
import com.example.pokedex.presentation.viewmodel.DetailViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPokemonFragment: BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel by viewModels<DetailViewModel>()
    private val evolutionsAdapter by lazy(LazyThreadSafetyMode.NONE) { PokemonEvolutionAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val data = arguments?.getParcelable<PokemonEntity>(EXTRA_DATA)
        data?.let { initView(it) }

        binding.ivBack.setOnClickListener {
            activity?.finish()
        }

        observeDetails()

    }

    private fun initView(data: PokemonEntity) {
        with(binding) {
            ivPokemon.loadImage(data.imageUrl)
            tvPokeName.text = data.pokeName
            tvPokeNumber.text = data.pokeNumber
        }

        val rawId = data.pokeNumber.removePrefix("#").toInt()
        viewModel.fetchDetails(rawId.toString())
        viewModel.fetchPokeEvolutions(rawId.toString())

        setColors(data.colorTypes)
        renderStatsColor(data.colorTypes)
    }

    private fun observeDetails() {
        viewModel.pokemonDetail.observe(viewLifecycleOwner){
            when(it) {
                is UiEvent.Loading -> {
                    showLoading()
                }
                is UiEvent.Success -> {
                    hideLoading()
                    renderUi(it.data)
                }
                is UiEvent.Error -> {
                    hideLoading()
                }
            }
        }

        viewModel.pokemonEvolution.observe(viewLifecycleOwner) {
            when(it) {
                is UiEvent.Loading -> {}
                is UiEvent.Success -> {
                    evolutionsAdapter.submitList(it.data)
                }
                is UiEvent.Error -> {}
            }
        }
    }

    private fun showLoading() {
        with(binding){
            container.gone()
            shimmerContent.visible()
        }
    }

    private fun hideLoading() {
        with(binding) {
            container.visible()
            shimmerContent.gone()
        }
    }

    private fun renderUi(data: PokemonDetailEntity) {
        renderAttributes(data)
        renderStats(data)
        renderTypes(data)
        renderEvolutions()
    }

    private fun renderTypes(data: PokemonDetailEntity) {
        binding.containerTypes.removeAllViews()
        data.types.forEach {
            val chips = Chip(requireContext())
            chips.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            chips.setChipBackgroundColorResource(getColorFromDetailTypes(it.type.name))
            chips.text = it.type.name
            binding.containerTypes.setChipSpacing(requireContext().resources.getDimensionPixelSize(R.dimen.chips_spacing))
            binding.containerTypes.addView(chips)
        }
    }

    private fun renderAttributes(data: PokemonDetailEntity) {
        with(binding.viewAttributes) {
            tvHeight.text = data.getHeightString()
            tvWeight.text = data.getWeightString()
            tvMoves.text = data.move
        }
        binding.tvPokeDesc.text = data.ability
    }

    private fun renderStats(data: PokemonDetailEntity) {
        with(binding.viewStats) {
            progressHp.setProgress(data.hp, true)
            progressAtk.setProgress(data.attack, true)
            progressDef.setProgress(data.defense, true)
            progressSdef.setProgress(data.specialDefense, true)
            progressSatk.setProgress(data.specialAttack, true)
            progressSpd.setProgress(data.speed, true)

            tvAtkValue.text = data.attack.toValueFormatted()
            tvHpValue.text = data.hp.toValueFormatted()
            tvDefValue.text = data.defense.toValueFormatted()
            tvSdefValue.text = data.specialDefense.toValueFormatted()
            tvSatkValue.text = data.specialAttack.toValueFormatted()
            tvSpdValue.text = data.speed.toValueFormatted()
        }
    }

    private fun renderStatsColor(types: String){
        with(binding.viewStats){
            progressHp.setColor(types)
            progressAtk.setColor(types)
            progressDef.setColor(types)
            progressSdef.setColor(types)
            progressSatk.setColor(types)
            progressSpd.setColor(types)
        }
    }

    private fun renderEvolutions() {
        with(binding.rvEvolutions) {
            adapter = evolutionsAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        evolutionsAdapter.setOnClickListener { initView(it) }
    }

    private fun Int.toValueFormatted(): String {
        return String.format("%03d", this)
    }


    private fun setColors(colorTypes: String) {
        with(binding) {
            container.setBackgroundResource(getColorRes(colorTypes))
            tvAbout.setTextColor(colorTypes)
            tvBaseStats.setTextColor(colorTypes)
            tvEvolutions.setTextColor(colorTypes)
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