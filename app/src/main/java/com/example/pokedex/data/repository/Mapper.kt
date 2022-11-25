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
            response.ability,
            response.types,
            hp = response.stats[INDEX_HP].baseStat,
            attack = response.stats[INDEX_ATTACK].baseStat,
            defense = response.stats[INDEX_DEFENSE].baseStat,
            speed = response.stats[INDEX_SPEED].baseStat,
            specialAttack = response.stats[INDEX_SPECIAL_ATTACK].baseStat,
            specialDefense = response.stats[INDEX_SPECIAL_DEFENSE].baseStat,
            move = response.moves[0].move.name
        )
    }

    private companion object {
        const val INDEX_HP = 0
        const val INDEX_ATTACK = 1
        const val INDEX_DEFENSE = 2
        const val INDEX_SPECIAL_ATTACK = 3
        const val INDEX_SPECIAL_DEFENSE = 4
        const val INDEX_SPEED = 5
    }
}