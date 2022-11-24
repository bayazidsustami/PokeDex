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

    fun getAllPokemon(): Flow<List<PokemonEntity>> {
        return dao.getAllPokemon()
    }
}