package com.example.pokedex.data.datasource.remote.networking

import com.example.pokedex.common.Constant
import com.example.pokedex.data.datasource.remote.response.PokemonDetail
import com.example.pokedex.data.datasource.remote.response.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Constant.Endpoints.HOME)
    suspend fun getPokemonList(
        @Query("limit") limit: Int = Constant.Values.COUNT_LIMIT
    ): PokemonListResponse

    @GET(Constant.Endpoints.DETAIL)
    suspend fun getPokemonDetail(
        @Path("name") id: String
    ): PokemonDetail
}