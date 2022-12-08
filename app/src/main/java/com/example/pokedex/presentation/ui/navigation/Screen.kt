package com.example.pokedex.presentation.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Detail: Screen("detail/{pokemonEntity}"){
        fun createRoute(arg: String) = "detail/$arg"
    }
    object Profile: Screen("profile")
}