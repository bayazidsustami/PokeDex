package com.example.pokedex.data.datasource.remote.response

import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class PokemonDetail(
    @SerializedName( "id")
    val id: Int = 0,
    @SerializedName( "name")
    val name: String = "",
    @SerializedName( "height")
    val height: Int = 0,
    @SerializedName( "weight")
    val weight: Int = 0,
    @SerializedName( "base_experience")
    val experience: Int = 0,
    @SerializedName( "types")
    val types: List<TypeResponse> = emptyList(),
    @SerializedName("stats")
    val stats: List<Stats> = emptyList(),
    @SerializedName("moves")
    val moves: List<Moves> = emptyList()
) {

    data class TypeResponse(
        @SerializedName( "slot")
        val slot: Int = 0,
        @SerializedName( "type")
        val type: Type = Type()
    )

    data class Type(
        @SerializedName( "name") val name: String = ""
    )

    class Stats(

        @SerializedName("stat")
        val stat: Stat = Stat(),

        @SerializedName("base_stat")
        val baseStat: Int = 0,

        @SerializedName("effort")
        val effort: Int = 0
    )

    class Stat(
        @SerializedName("name")
        val name: String = "",
    )

    data class Moves(@SerializedName("move") val move: Move = Move())

    class Move(
        @SerializedName("name")
        val name: String = "",

        @SerializedName("url")
        val url: String = ""
    )

    companion object {
        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
        const val maxExp = 1000
    }
}