package com.example.pokedex.data.repository

import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.datasource.remote.response.PokemonDetail
import com.example.pokedex.data.datasource.remote.response.PokemonEvolution
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

    fun mapToEvolutionToEntity(response: PokemonEvolution): List<PokemonEntity> {
        val itemsPokemon:MutableList<PokemonEntity> = mutableListOf()
        val first = response.chain.species

        itemsPokemon.add(
            PokemonEntity(
            pokeNumber = first.getPokeNumber(),
            pokeName = first.name,
            url = first.url,
            imageUrl = first.getImageUrl(),
            ""
        ))

        if (response.chain.evolvesTo.isNotEmpty()) {
            val second = response.chain.evolvesTo.first()
            itemsPokemon.add(
                PokemonEntity(
                    pokeNumber = second.species.getPokeNumber(),
                    pokeName = second.species.name,
                    url = second.species.url,
                    imageUrl = second.species.getImageUrl(),
                    ""
                ))

            if (second.evolvesTo.isNotEmpty()) {
                val third = second.evolvesTo.first()
                itemsPokemon.add(
                    PokemonEntity(
                        pokeNumber = third.species.getPokeNumber(),
                        pokeName = third.species.name,
                        url = third.species.url,
                        imageUrl = third.species.getImageUrl(),
                        ""
                    ))
            }
        }
        return itemsPokemon
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