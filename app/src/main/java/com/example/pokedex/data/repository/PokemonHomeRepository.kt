package com.example.pokedex.data.repository

import com.example.pokedex.common.PokeSort
import com.example.pokedex.common.Resource
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.datasource.remote.response.PokemonListResponse
import kotlinx.coroutines.flow.Flow

interface PokemonHomeRepository {
    fun getListPokemon(name: String, orderBy: PokeSort): Flow<Resource<List<PokemonEntity>>>
}