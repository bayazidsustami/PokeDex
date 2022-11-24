package com.example.pokedex.domain

import com.example.pokedex.common.PokeSort
import com.example.pokedex.common.Resource
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.repository.PokemonHomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonHomeUseCaseImpl @Inject constructor(
    private val repository: PokemonHomeRepository
): PokemonHomeUseCase {

    override fun getListPokemon(
        name: String,
        orderBy: PokeSort
    ): Flow<Resource<List<PokemonEntity>>> {
        return repository.getListPokemon(name, orderBy)
    }
}