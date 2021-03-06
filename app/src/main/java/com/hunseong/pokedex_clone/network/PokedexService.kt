package com.hunseong.pokedex_clone.network

import com.hunseong.pokedex_clone.model.PokemonInfo
import com.hunseong.pokedex_clone.model.PokemonResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {
    // limit - 한 번의 network request에서 가져올 pokemon의 수
    // offset - 몇 번째 pokemon부터 가져올 지
    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(
        @Path("name") name: String
    ) : PokemonInfo

}