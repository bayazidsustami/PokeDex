package com.example.pokedex.presentation.ui

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.common.PokeDetailType
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.presentation.ui.navigation.Screen
import com.example.pokedex.presentation.ui.screen.DetailScreen
import com.example.pokedex.presentation.ui.screen.HomeScreen
import com.example.pokedex.presentation.ui.screen.ProfileScreen
import com.google.gson.Gson

@Composable
fun PokeDexApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){
    Scaffold(
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(it)
        ){
            composable(Screen.Home.route){
                HomeScreen(
                    onItemClicked = { pokeEntity ->
                        val pokeEntityJson = Uri.encode(Gson().toJson(pokeEntity))
                        navController.navigate(Screen.Detail.createRoute(pokeEntityJson))
                    },
                    onNavigateProfile = {
                        navController.navigate(Screen.Profile.route)
                    }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("pokemonEntity"){
                        type = PokeDetailType()
                    }
                )
            ){ navBackStackEntry ->
                val pokemonEntity = navBackStackEntry.arguments?.getParcelable<PokemonEntity>("pokemonEntity")
                DetailScreen(
                    onBackPressed = { navController.navigateUp() },
                    itemColor = pokemonEntity?.colorTypes ?: "",
                    pokeNumber = pokemonEntity?.pokeNumber ?: "",
                    pokeImage = pokemonEntity?.imageUrl ?: "",
                    pokemonName = pokemonEntity?.pokeName ?: ""
                )
            }
            composable(Screen.Profile.route){
                ProfileScreen(
                    onNavigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}