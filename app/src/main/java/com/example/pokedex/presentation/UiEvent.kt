package com.example.pokedex.presentation

sealed interface UiEvent<out T> {
    object Loading: UiEvent<Nothing>
    data class Success<T>(val data: T) : UiEvent<T>
    data class Error(val message: String, val errorCode: Int) : UiEvent<Nothing>
}