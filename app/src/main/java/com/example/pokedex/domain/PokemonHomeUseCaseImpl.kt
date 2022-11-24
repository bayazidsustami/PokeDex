package com.example.pokedex.domain

import com.example.pokedex.common.PokeSort
import com.example.pokedex.common.Resource
import com.example.pokedex.data.datasource.remote.response.Pokemon
import com.example.pokedex.data.repository.PokemonHomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonHomeUseCaseImpl @Inject constructor(
    private val repository: PokemonHomeRepository,
    private val domainMapper: DomainMapper
): PokemonHomeUseCase {

    override fun getListPokemon(name: String, orderBy: PokeSort): Flow<Resource<List<Pokemon>>> {
        val result = repository.getListPokemon(name, orderBy)
        return result.map { domainMapper.toDomain(it){
                response -> domainMapper.pokeListToDomain(response)
        } }
    }
}