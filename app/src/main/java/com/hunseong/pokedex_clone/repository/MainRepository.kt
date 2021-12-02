package com.hunseong.pokedex_clone.repository

import com.hunseong.pokedex_clone.db.PokemonDao
import com.hunseong.pokedex_clone.model.Result
import com.hunseong.pokedex_clone.network.PokedexClient
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val pokedexClient: PokedexClient,
    private val pokemonDao: PokemonDao,
) : Repository {

    fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
    ): Flow<Result> = flow {
        emit(Result.Loading)
            var pokemons = pokemonDao.getPokemonList(page)
            if (pokemons.isEmpty()) {
                pokemons = pokedexClient.fetchPokemonList(page).results
                pokemons.forEach { pokemon -> pokemon.page = page }
                pokemonDao.insertPokemonList(pokemons)
                emit(Result.Success(pokemonDao.getAllPokemonList(page)))
            } else {
                emit(Result.Success(pokemonDao.getAllPokemonList(page)))
            }
    }.onStart { onStart() }.catch { e -> emit(Result.Error(e)) }
}