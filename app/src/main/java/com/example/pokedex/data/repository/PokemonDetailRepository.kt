package com.example.pokedex.data.repository

import com.example.pokedex.common.Resource
import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonDetailRepository {
    fun getPokemon(id: String): Flow<Resource<PokemonDetailEntity>>
    fun getPokemonEvolution(id: String): Flow<Resource<List<PokemonEntity>>>
}