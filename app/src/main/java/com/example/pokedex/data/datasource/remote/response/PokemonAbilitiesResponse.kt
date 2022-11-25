package com.example.pokedex.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class PokemonAbilitiesResponse (
    @SerializedName("effect_entries")
    val effectEntries: List<Effect> = emptyList()
) {
    class Effect(

        @SerializedName("short_effect")
        val shortEffect: String = "" ,

        @SerializedName("effect")
        val effect: String = "",
    )
}