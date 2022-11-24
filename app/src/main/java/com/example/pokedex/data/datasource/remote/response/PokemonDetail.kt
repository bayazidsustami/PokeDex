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
    val hp: Int = Random.nextInt(maxHp),
    val attack: Int = Random.nextInt(maxAttack),
    val defense: Int = Random.nextInt(maxDefense),
    val speed: Int = Random.nextInt(maxSpeed),
    val exp: Int = Random.nextInt(maxExp)
) {

    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getHpString(): String = " $hp/$maxHp"
    fun getAttackString(): String = " $attack/$maxAttack"
    fun getDefenseString(): String = " $defense/$maxDefense"
    fun getSpeedString(): String = " $speed/$maxSpeed"
    fun getExpString(): String = " $exp/$maxExp"

    data class TypeResponse(
        @SerializedName( "slot")
        val slot: Int = 0,
        @SerializedName( "type")
        val type: Type = Type()
    )

    data class Type(
        @SerializedName( "name") val name: String = ""
    )

    companion object {
        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
        const val maxExp = 1000
    }
}