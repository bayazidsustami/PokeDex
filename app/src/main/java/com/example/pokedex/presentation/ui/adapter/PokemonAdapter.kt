package com.example.pokedex.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.data.datasource.remote.response.Pokemon
import com.example.pokedex.databinding.ViewPokemonItemBinding
import com.example.pokedex.presentation.loadImage

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private var items: List<Pokemon> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewPokemonItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitList(items: List<Pokemon>){
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ViewPokemonItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Pokemon) {
            with(binding) {
                tvPokeNumber.text = data.getPokeNumber()
                ivPokemon.loadImage(data.getImageUrl())
                tvPokeName.text = data.name
            }
        }
    }
}