package com.example.pokedex.data.repository

import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.datasource.remote.response.PokemonDetail
import com.example.pokedex.data.datasource.remote.response.PokemonListResponse
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapResponseToEntity(response: PokemonListResponse): List<PokemonEntity> {
        return response.results.map {
            PokemonEntity(
                pokeName = it.name,
                url = it.url,
                imageUrl = it.getImageUrl(),
                pokeNumber = it.getPokeNumber(),
                colorTypes = it.getRandomColorTypes()
            )
        }
    }

    fun mapDetailResponseToEntity(response: PokemonDetail): PokemonDetailEntity {
        return PokemonDetailEntity(
            response.id,
            response.name,
            response.height,
            response.weight,
            response.experience,
            response.types,
            response.hp,
            response.attack,
            response.defense,
            response.speed,
            response.exp
        )
    }
}