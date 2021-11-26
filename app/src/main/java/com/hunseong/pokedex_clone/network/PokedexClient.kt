package com.hunseong.pokedex_clone.network

import com.hunseong.pokedex_clone.model.PokemonResponse
import retrofit2.Response
import javax.inject.Inject

class PokedexClient @Inject constructor(
    private val pokedexService: PokedexService
) {

    // page * PAGING_SIZE(20) index 부터, PAGING_SIZE(20)만큼 pokemon list GET
    suspend fun fetchPokemonList(page: Int) : Response<PokemonResponse> {
        return pokedexService.fetchPokemonList(limit = PAGING_SIZE, offset = page * PAGING_SIZE)
    }

    companion object {
        private const val PAGING_SIZE = 20
    }
}