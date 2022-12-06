package com.example.pokedex.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pokedex.presentation.ui.screen.HomeScreen

@Composable
fun PokeDexApp(
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = modifier
    ) {
        HomeScreen(
            modifier = modifier.padding(it),
            onItemClicked = {

            },
        )
    }
}