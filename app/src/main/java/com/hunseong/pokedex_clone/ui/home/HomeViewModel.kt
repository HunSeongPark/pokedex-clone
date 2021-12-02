package com.hunseong.pokedex_clone.ui.home

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.pokedex_clone.model.Result
import com.hunseong.pokedex_clone.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    var isLoading: Boolean = false

    val list: StateFlow<Result> = pokemonFetchingIndex.flatMapLatest { page ->
        mainRepository.fetchPokemonList(page) { isLoading = true }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Result.Uninitialized
    )


    @MainThread
    fun fetchNextPokemonList() {
        if (!isLoading) pokemonFetchingIndex.value++
    }
}