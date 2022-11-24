package com.example.pokedex.common

sealed interface Resource<out T> {
    object Loading: Resource<Nothing>
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val message: String, val errorCode: Int) : Resource<Nothing>
}