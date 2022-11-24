package com.example.pokedex.data.datasource.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListResponse(
    @field:Json(name = "count")
    val count: Int = 0,
    @field:Json(name = "next")
    val next: String = "",
    @field:Json(name = "previous")
    val previous: String = "",
    @field:Json(name = "results")
    val results: List<Pokemon> = emptyList()
)