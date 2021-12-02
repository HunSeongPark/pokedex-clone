package com.hunseong.pokedex_clone.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.pokedex_clone.model.Pokemon
import com.hunseong.pokedex_clone.model.PokemonInfo
import com.hunseong.pokedex_clone.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    state: SavedStateHandle,
    detailRepository: DetailRepository,
) : ViewModel() {

    private val pokemon: Pokemon = state.get("pokemon")!!

    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)

    val toast: MutableStateFlow<Throwable?> = MutableStateFlow(null)

    val pokemonInfo: StateFlow<PokemonInfo?> =
        detailRepository.fetchPokemonInfo(
            name = pokemon.name,
            onError = { e -> toast.value = e },
            onComplete = { isLoading.value = false })
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )
}