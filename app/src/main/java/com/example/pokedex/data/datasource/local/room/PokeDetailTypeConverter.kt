package com.example.pokedex.data.datasource.local.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.pokedex.data.datasource.remote.response.PokemonDetail
import com.google.gson.Gson
import javax.inject.Inject

@ProvidedTypeConverter
class PokeDetailTypeConverter @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun fromString(value: String): List<PokemonDetail.TypeResponse> {
        return gson.fromJson(value, Array<PokemonDetail.TypeResponse>::class.java).toList()
    }

    @TypeConverter
    fun fromType(type: List<PokemonDetail.TypeResponse>): String {
        return gson.toJson(type)
    }
}