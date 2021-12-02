package com.hunseong.pokedex_clone.ui.main

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.pokedex_clone.model.Result
import com.hunseong.pokedex_clone.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    val list: StateFlow<Result> = pokemonFetchingIndex.flatMapLatest { page ->
        mainRepository.fetchPokemonList(page)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Result.Uninitialized
    )


    @MainThread
    fun fetchNextPokemonList() {
        pokemonFetchingIndex.value++
    }
}