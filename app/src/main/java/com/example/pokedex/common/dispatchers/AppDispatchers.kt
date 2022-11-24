package com.example.pokedex.common.dispatchers

import kotlinx.coroutines.Dispatchers

class AppDispatchers : DispatcherProvider {
    override fun main() = Dispatchers.Main
    override fun io() = Dispatchers.IO
}