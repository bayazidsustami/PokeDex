package com.example.pokedex.data.datasource.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon_entity WHERE poke_name LIKE '%' || :query || '%'")
    fun getAllPokemon(query: String): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon_entity WHERE poke_name LIKE '%' || :query || '%' ORDER BY poke_name ASC")
    fun getAllPokemonAsc(query: String): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon_entity WHERE poke_name LIKE '%' || :query || '%' ORDER BY poke_name DESC")
    fun getAllPokemonDesc(query: String): Flow<List<PokemonEntity>>
}