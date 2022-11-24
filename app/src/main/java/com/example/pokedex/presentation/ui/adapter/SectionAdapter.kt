package com.example.pokedex.presentation.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.presentation.ui.fragment.DetailPokemonFragment

class SectionAdapter(
    activity: FragmentActivity,
    private val items: List<PokemonEntity>
): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {
        val fragment = DetailPokemonFragment()
        fragment.arguments = Bundle().apply {
            putParcelable(DetailPokemonFragment.EXTRA_DATA, items[position])
        }
        return fragment
    }
}