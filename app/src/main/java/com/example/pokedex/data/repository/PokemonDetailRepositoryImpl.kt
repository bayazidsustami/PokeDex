package com.example.pokedex.data.repository

import com.example.pokedex.common.Resource
import com.example.pokedex.data.datasource.local.PokeDetailLocalDataSource
import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.datasource.remote.PokeDetailRemoteDataSource
import com.example.pokedex.data.datasource.remote.utils.NetworkResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonDetailRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokeDetailRemoteDataSource,
    private val localDataSource: PokeDetailLocalDataSource,
    private val resource: NetworkResource,
    private val mapper: Mapper
): PokemonDetailRepository {

    override fun getPokemon(id: String): Flow<Resource<PokemonDetailEntity>> {
        return resource.networkBoundResource(
            loadFromDb = {
                localDataSource.getPokemon(id)
            },
            createCall = {
                remoteDataSource.getDetailPokemon(id)
            },
            shouldFetch = { it?.name.isNullOrEmpty() },
            saveCallResult = {
                localDataSource.insertPokemon(mapper.mapDetailResponseToEntity(it))
            }
        )
    }

    override fun getPokemonEvolution(id: String): Flow<Resource<List<PokemonEntity>>> {
        return resource.fetchFromServer(
            fetch = {
                remoteDataSource.getPokemonEvolution(id)
            },
            mapper = { mapper.mapToEvolutionToEntity(it) }
        )
    }

}