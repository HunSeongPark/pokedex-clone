package com.hunseong.pokedex_clone.repository

import com.hunseong.pokedex_clone.db.PokemonDao
import com.hunseong.pokedex_clone.model.Result
import com.hunseong.pokedex_clone.network.PokedexClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val pokedexClient: PokedexClient,
    private val pokemonDao: PokemonDao,
    private val ioDispatcher: CoroutineDispatcher,
) : Repository {

    fun fetchPokemonList(page: Int): Flow<Result> = flow {
        emit(Result.Loading)
        var pokemons = pokemonDao.getPokemonList(page)
        if (pokemons.isEmpty()) {
            val response = pokedexClient.fetchPokemonList(page)
            if (response.isSuccessful) {
                pokemons = response.body()!!.results
                pokemons.forEach { pokemon -> pokemon.page = page }
                pokemonDao.insertPokemonList(pokemons)
                emit(Result.Success(pokemonDao.getAllPokemonList(page)))
            } else {
                emit(Result.Error)
            }
        } else {
            emit(Result.Success(pokemonDao.getAllPokemonList(page)))
        }
    }
}