package com.example.pokedex.data.datasource.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonInfo: PokemonDetailEntity)

    @Query("SELECT * FROM pokemon_detail_entity WHERE id = :id")
    fun getPokemonInfo(id: String): Flow<PokemonDetailEntity>
}