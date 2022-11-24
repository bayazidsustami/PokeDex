package com.example.pokedex.data.datasource.remote

import com.example.pokedex.common.ApiResponse
import com.example.pokedex.common.Constant
import com.example.pokedex.common.dispatchers.di.qulifiers.IODispatcher
import com.example.pokedex.data.datasource.remote.networking.ApiService
import com.example.pokedex.data.datasource.remote.response.PokemonListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import retrofit2.HttpException
import javax.inject.Inject

class PokeHomeRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getListPokemon(): Flow<ApiResponse<PokemonListResponse>> {
        return flow {
            try {
                val response = apiService.getPokemonList()
                if (response.results.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Fail("empty list", 400))
                }
            } catch (e: HttpException){
                emit(ApiResponse.Fail(e.message(), e.code()))
            }catch (e: Exception){
                emit(ApiResponse.Fail(e.message.toString(), 400))
            }
        }.retry(Constant.Values.RETRY_TIME) {
            it is HttpException
        }.flowOn(dispatcher)
    }

}