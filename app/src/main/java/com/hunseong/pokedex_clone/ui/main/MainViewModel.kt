package com.hunseong.pokedex_clone.ui.main

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.pokedex_clone.model.Pokemon
import com.hunseong.pokedex_clone.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _toastMessage = MutableLiveData<String?>(null)
    val toastMessage: LiveData<String?>
        get() = _toastMessage

    private val _list = MutableLiveData<List<Pokemon>>(emptyList())
    val list: LiveData<List<Pokemon>>
        get() = _list

    private var page = 0

    @MainThread
    fun fetchNextPokemonList() = viewModelScope.launch {
        _isLoading.value = true
        val list = mainRepository.fetchPokemonList(page)
        if (list.isNullOrEmpty()) {
            _toastMessage.value = "Http Response Failed"
        } else {
            _list.value = list
            _toastMessage.value = "Http Response Success"
            page++
        }
        _isLoading.value = false
    }
}