package com.example.pokedex.data.datasource.remote

import com.example.pokedex.common.ApiResponse
import com.example.pokedex.common.Constant
import com.example.pokedex.common.dispatchers.di.qulifiers.IODispatcher
import com.example.pokedex.data.datasource.remote.networking.ApiService
import com.example.pokedex.data.datasource.remote.response.PokemonDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import retrofit2.HttpException
import javax.inject.Inject

class PokeDetailRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getDetailPokemon(id: String): Flow<ApiResponse<PokemonDetail>> {
        return flow {
            try {
                val response = apiService.getPokemonDetail(id)
                emit(ApiResponse.Success(response))
            } catch (e: HttpException){
                emit(ApiResponse.Fail(e.message(), e.code()))
            } catch (e: Exception){
                emit(ApiResponse.Fail(e.message.toString(), 400))
            }
        }.retry(Constant.Values.RETRY_TIME) {
            it is HttpException
        }.flowOn(dispatcher)
    }
}