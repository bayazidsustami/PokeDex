package com.example.pokedex.data.datasource.local

import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import com.example.pokedex.data.datasource.local.room.dao.PokemonDetailDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokeDetailLocalDataSource @Inject constructor(
    private val dao: PokemonDetailDao
) {

    suspend fun insertPokemon(pokemonDetail: PokemonDetailEntity) {
        dao.insertPokemon(pokemonDetail)
    }

    fun getPokemon(id: String) : Flow<PokemonDetailEntity> {
        return dao.getPokemonInfo(id)
    }
}