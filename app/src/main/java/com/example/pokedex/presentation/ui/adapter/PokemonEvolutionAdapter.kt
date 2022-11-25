package com.example.pokedex.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.databinding.ViewPokemonEvolutionItemBinding
import com.example.pokedex.presentation.getColorRes
import com.example.pokedex.presentation.loadImage

class PokemonEvolutionAdapter: RecyclerView.Adapter<PokemonEvolutionAdapter.ViewHolder>() {

    private var items: List<PokemonEntity> = emptyList()

    private var clickListener: OnClickListener? = null

    fun setOnClickListener(clickListener: OnClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewPokemonEvolutionItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        holder.binding.root.setOnClickListener {
            clickListener?.onClick(position)
        }
    }

    override fun getItemCount(): Int = items.size

    fun submitList(items: List<PokemonEntity>){
        this.items.toMutableList().clear()
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        val binding: ViewPokemonEvolutionItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PokemonEntity) {
            with(binding) {
                tvPokeNumber.text = data.pokeNumber
                ivPokemon.loadImage(data.imageUrl)
                tvPokeName.text = data.pokeName
                tvPokeName.setTextColor(ContextCompat.getColor(itemView.context, getColorRes(data.colorTypes)))

            }
        }
    }

    fun interface OnClickListener {
        fun onClick(position: Int)
    }
}