package com.example.pokedex.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex.common.Constant
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.datasource.local.room.dao.PokemonDao

@Database(
    entities = [PokemonEntity::class],
    version = Constant.Values.DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}