package com.example.pokedex.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokedex.common.Constant

@Entity(tableName = Constant.Values.POKEMON_TABLE)
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "poke_number")
    val pokeNumber: String,
    @ColumnInfo(name = "poke_name")
    val pokeName: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
)
