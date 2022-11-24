package com.example.pokedex.data.datasource.local

import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.datasource.local.room.dao.PokemonDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokeHomeLocalDataSource @Inject constructor(
    private val dao: PokemonDao
){

    suspend fun insertAll(items: List<PokemonEntity>) {
        dao.insertAll(items)
    }

    fun getAllPokemon(query: String): Flow<List<PokemonEntity>> {
        return dao.getAllPokemon(query)
    }

    fun getAllPokemonAsc(query: String): Flow<List<PokemonEntity>> {
        return dao.getAllPokemonAsc(query)
    }

    fun getAllPokemonDesc(query: String): Flow<List<PokemonEntity>> {
        return dao.getAllPokemonDesc(query)
    }
}