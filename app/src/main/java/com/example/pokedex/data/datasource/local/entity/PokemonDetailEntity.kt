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
    @ColumnInfo(name = "ability")
    val ability: String,
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
    @ColumnInfo(name="special_attack")
    val specialAttack: Int,
    @ColumnInfo(name="special_defense")
    val specialDefense: Int,
    @ColumnInfo(name="move")
    val move: String
) {
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
}
