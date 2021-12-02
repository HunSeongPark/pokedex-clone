package com.hunseong.pokedex_clone.di

import com.hunseong.pokedex_clone.db.PokemonDao
import com.hunseong.pokedex_clone.network.PokedexClient
import com.hunseong.pokedex_clone.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(ViewModelComponent::class)
@Module
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        pokedexClient: PokedexClient,
        pokemonDao: PokemonDao,
    ) : MainRepository {
        return MainRepository(pokedexClient, pokemonDao)
    }
}