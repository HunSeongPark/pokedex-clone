package com.hunseong.pokedex_clone.model

sealed class Result {
    object Uninitialized : Result()

    object Loading : Result()

    data class Success<T>(val data: T) : Result()

    object Error : Result()
}