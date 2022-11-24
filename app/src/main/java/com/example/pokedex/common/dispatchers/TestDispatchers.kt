package com.example.pokedex.common.dispatchers

import kotlinx.coroutines.Dispatchers

class TestDispatchers: DispatcherProvider {
    override fun main() = Dispatchers.Unconfined
    override fun io() = Dispatchers.Unconfined
}