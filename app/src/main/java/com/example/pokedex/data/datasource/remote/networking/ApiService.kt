package com.example.pokedex.data.datasource.remote.networking

import com.example.pokedex.common.Constant
import com.example.pokedex.data.datasource.remote.response.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Constant.Endpoints.HOME)
    suspend fun getPokemonList(): PokemonListResponse

    @GET(Constant.Endpoints.DETAIL)
    suspend fun getPokemonDetail(
        @Path("name") name: String
    )
}