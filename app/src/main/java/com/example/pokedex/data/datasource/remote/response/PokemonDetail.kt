package com.example.pokedex.data.datasource.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.random.Random

@JsonClass(generateAdapter = true)
data class PokemonDetail(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "height")
    val height: Int = 0,
    @Json(name = "weight")
    val weight: Int = 0,
    @Json(name = "base_experience")
    val experience: Int = 0,
    @Json(name = "types")
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

    @JsonClass(generateAdapter = true)
    data class TypeResponse(
        @Json(name = "slot")
        val slot: Int = 0,
        @Json(name = "type")
        val type: Type = Type()
    )

    @JsonClass(generateAdapter = true)
    data class Type(
        @Json(name = "name") val name: String = ""
    )

    companion object {
        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
        const val maxExp = 1000
    }
}