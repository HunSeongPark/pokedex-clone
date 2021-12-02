package com.hunseong.pokedex_clone.repository

import com.hunseong.pokedex_clone.db.PokemonInfoDao
import com.hunseong.pokedex_clone.network.PokedexClient
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val pokedexClient: PokedexClient,
    private val pokemonInfoDao: PokemonInfoDao,
) : Repository {

    fun fetchPokemonInfo(
        name: String,
        onError: (Throwable) -> Unit,
        onComplete: () -> Unit,
    ) = flow {
        val pokemonInfo = pokemonInfoDao.getPokemonInfo(name)
        if (pokemonInfo == null) {
            val response = pokedexClient.fetchPokemonInfo(name)
            pokemonInfoDao.insertPokemonInfo(response)
            emit(response)
        } else {
            emit(pokemonInfo)
        }
    }.catch { e ->
        onError(e)
    }.onCompletion { onComplete() }
}