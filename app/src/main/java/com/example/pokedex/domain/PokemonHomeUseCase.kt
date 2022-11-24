package com.example.pokedex.domain

import com.example.pokedex.common.PokeSort
import com.example.pokedex.common.Resource
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonHomeUseCase {
    fun getListPokemon(name: String, orderBy: PokeSort): Flow<Resource<List<PokemonEntity>>>
}