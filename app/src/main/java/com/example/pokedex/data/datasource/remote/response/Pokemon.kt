package com.example.pokedex.data.datasource.remote.response

import com.example.pokedex.presentation.listPokeColors
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
){

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }

    fun getPokeNumber(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return String.format("#%03d", index.toInt())
    }

    fun getRandomColorTypes(): String {
        val randIndex = (0..8).random()
        return listPokeColors[randIndex]
    }
}