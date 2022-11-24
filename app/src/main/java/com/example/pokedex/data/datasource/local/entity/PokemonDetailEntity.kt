package com.example.pokedex.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokedex.common.Constant
import com.example.pokedex.data.datasource.remote.response.PokemonDetail

@Entity(tableName = Constant.Values.POKEMON_DETAIL_TABLE)
data class PokemonDetailEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name="height")
    val height: Int,
    @ColumnInfo(name = "weight")
    val weight: Int,
    @ColumnInfo(name = "base_experience")
    val baseExperience: Int,
    @ColumnInfo(name = "types")
    val types: List<PokemonDetail.TypeResponse>,
    @ColumnInfo(name = "hp")
    val hp: Int,
    @ColumnInfo(name = "attack")
    val attack: Int,
    @ColumnInfo(name = "defense")
    val defense: Int,
    @ColumnInfo(name = "speed")
    val speed: Int,
    @ColumnInfo(name = "exp")
    val exp:Int
)
