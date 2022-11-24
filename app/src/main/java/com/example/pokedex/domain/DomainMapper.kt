package com.example.pokedex.domain

import com.example.pokedex.common.Resource
import com.example.pokedex.data.datasource.remote.response.Pokemon
import com.example.pokedex.data.datasource.remote.response.PokemonListResponse
import javax.inject.Inject

class DomainMapper @Inject constructor(){

    inline fun <T, R> toDomain(
        result: Resource<T>,
        crossinline onResult: (T) -> R
    ): Resource<R> {
        return when(result) {
            is Resource.Loading -> Resource.Loading
            is Resource.Success -> Resource.Success(onResult(result.data))
            is Resource.Error -> Resource.Error(result.message, result.errorCode)
        }
    }

    fun pokeListToDomain(response: PokemonListResponse): List<Pokemon> {
        return response.results
    }

}