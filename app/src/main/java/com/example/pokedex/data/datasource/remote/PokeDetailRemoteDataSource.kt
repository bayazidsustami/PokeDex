package com.example.pokedex.data.datasource.remote

import com.example.pokedex.common.dispatchers.di.qulifiers.IODispatcher
import com.example.pokedex.data.datasource.remote.networking.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class PokeDetailRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getDetailPokemon(name: String) {

    }
}