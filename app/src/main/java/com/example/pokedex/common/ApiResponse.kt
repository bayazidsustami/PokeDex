package com.example.pokedex.common

sealed interface ApiResponse<out T> {
    data class Success<out T>(val data: T): ApiResponse<T>
    data class Fail(val message: String, val errorCode: Int): ApiResponse<Nothing>
}