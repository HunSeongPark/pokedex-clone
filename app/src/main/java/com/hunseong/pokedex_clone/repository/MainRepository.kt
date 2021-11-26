package com.hunseong.pokedex_clone.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.hunseong.pokedex_clone.db.PokemonDao
import com.hunseong.pokedex_clone.model.Pokemon
import com.hunseong.pokedex_clone.network.PokedexClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val pokedexClient: PokedexClient,
    private val pokemonDao: PokemonDao,
    private val ioDispatcher: CoroutineDispatcher,
) : Repository {

    // @WorkerThread annotation을 통해 해당 method가 WorkerThread에서 동작할 수 있도록 제약
    // WorkerThread에서 동작하지 않을 시 Error 발생시킴
    @WorkerThread
    suspend fun fetchPokemonList(
        page: Int
    ): List<Pokemon> = withContext(ioDispatcher) {
        var pokemons = pokemonDao.getPokemonList(page)
        if (pokemons.isEmpty()) {
            val response = pokedexClient.fetchPokemonList(page)
            if (response.isSuccessful) {
                pokemons = response.body()!!.results
                pokemons.forEach { it.page = page }
                pokemonDao.insertPokemonList(pokemons)
                return@withContext pokemonDao.getAllPokemonList(page)
            } else {
                return@withContext emptyList()
            }
        } else {
            return@withContext pokemonDao.getAllPokemonList(page)
        }
    }
}