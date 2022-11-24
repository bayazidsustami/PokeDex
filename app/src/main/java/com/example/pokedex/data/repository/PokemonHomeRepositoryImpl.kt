package com.example.pokedex.data.repository

import com.example.pokedex.common.PokeSort
import com.example.pokedex.common.Resource
import com.example.pokedex.data.datasource.local.PokeHomeLocalDataSource
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.datasource.remote.PokeHomeRemoteDataSource
import com.example.pokedex.data.datasource.remote.utils.NetworkResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonHomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokeHomeRemoteDataSource,
    private val localDataSource: PokeHomeLocalDataSource,
    private val resource: NetworkResource,
    private val mapper: Mapper,
): PokemonHomeRepository {

    override fun getListPokemon(
        name: String,
        orderBy: PokeSort
    ): Flow<Resource<List<PokemonEntity>>> {
        return resource.networkBoundResource(
            loadFromDb = {
                localDataSource.getAllPokemon()
            },
            createCall = {
                remoteDataSource.getListPokemon()
            },
            shouldFetch = { it.isNullOrEmpty() },
            saveCallResult = {
                localDataSource.insertAll(mapper.mapResponseToEntity(it))
            }
        )
    }

}