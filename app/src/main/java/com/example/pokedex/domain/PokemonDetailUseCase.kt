package com.example.pokedex.domain

import com.example.pokedex.common.Resource
import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonDetailUseCase {
    fun getPokemon(id: String): Flow<Resource<PokemonDetailEntity>>
    fun getPokemonEvolution(id: String): Flow<Resource<List<PokemonEntity>>>
}