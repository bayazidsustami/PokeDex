package com.example.pokedex.data.repository

import android.util.Log
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.datasource.remote.response.PokemonListResponse
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapResponseToEntity(response: PokemonListResponse): List<PokemonEntity> {
        Log.d("RESULT", response.results.toString())
        return response.results.map {
            PokemonEntity(
                pokeName = it.name,
                url = it.url,
                imageUrl = it.getImageUrl(),
                pokeNumber = it.getPokeNumber()
            )
        }
    }
}