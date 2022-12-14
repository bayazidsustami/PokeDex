package com.example.pokedex.common.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun main(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
}