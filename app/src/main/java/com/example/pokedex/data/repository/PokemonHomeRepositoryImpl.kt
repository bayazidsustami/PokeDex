package com.example.pokedex.data.repository

import com.example.pokedex.common.PokeSort
import com.example.pokedex.common.Resource
import com.example.pokedex.data.datasource.remote.PokeHomeRemoteDataSource
import com.example.pokedex.data.datasource.remote.response.PokemonListResponse
import com.example.pokedex.data.datasource.remote.utils.NetworkResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonHomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokeHomeRemoteDataSource,
    private val resource: NetworkResource
): PokemonHomeRepository {

    override fun getListPokemon(
        name: String,
        orderBy: PokeSort
    ): Flow<Resource<PokemonListResponse>> {
        return resource.fetchFromServer(
            { remoteDataSource.getListPokemon() },
            { it }
        )
    }

}