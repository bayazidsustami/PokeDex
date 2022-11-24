package com.example.pokedex.common

object Constant {

    object Endpoints {
        const val HOME = "pokemon"
        const val DETAIL = "$HOME/{name}"
    }

    object Values {
        const val RETRY_TIME = 3L
        const val COUNT_LIMIT = 100
        const val POKEMON_TABLE = "pokemon_entity"
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "pokemon.db"
    }
}