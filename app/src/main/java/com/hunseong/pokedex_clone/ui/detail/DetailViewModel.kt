package com.hunseong.pokedex_clone.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hunseong.pokedex_clone.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(state: SavedStateHandle) : ViewModel() {

    private val pokemon: Pokemon = state.get("pokemon")!!


}