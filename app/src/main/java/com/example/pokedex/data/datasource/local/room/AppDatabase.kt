package com.example.pokedex.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokedex.common.Constant
import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.datasource.local.room.dao.PokemonDao
import com.example.pokedex.data.datasource.local.room.dao.PokemonDetailDao

@Database(
    entities = [PokemonEntity::class, PokemonDetailEntity::class],
    version = Constant.Values.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(value = [PokeDetailTypeConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonDetailDao(): PokemonDetailDao
}