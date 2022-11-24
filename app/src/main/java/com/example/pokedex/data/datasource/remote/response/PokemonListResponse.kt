package com.example.pokedex.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("next")
    val next: String = "",
    @SerializedName("previous")
    val previous: String = "",
    @SerializedName("results")
    val results: List<Pokemon> = emptyList()
)