package com.example.pokedex.domain

import com.example.pokedex.common.Resource
import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.repository.PokemonDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonDetailUseCaseImpl @Inject constructor(
    private val repository: PokemonDetailRepository
): PokemonDetailUseCase {

    override fun getPokemon(id: String): Flow<Resource<PokemonDetailEntity>> {
        return repository.getPokemon(id)
    }

    override fun getPokemonEvolution(id: String): Flow<Resource<List<PokemonEntity>>> {
        return repository.getPokemonEvolution(id)
    }
}