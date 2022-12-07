package com.example.pokedex.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.pokedex.presentation.ui.theme.DarkGray
import com.example.pokedex.presentation.ui.theme.PokeDexTheme
import com.example.pokedex.presentation.ui.theme.Poppins

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokeEvolutionItem(
    modifier: Modifier = Modifier,
    pokeName: String,
    pokeImage: String
) {
    Column(
        modifier = modifier
    ) {
        GlideImage(
            model = pokeImage,
            contentDescription = pokeName
        )
        Text(
            text = pokeName,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Poppins,
            lineHeight = 16.sp,
            color = DarkGray,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokeEvolutionPreview() {
    PokeDexTheme {
        PokeEvolutionItem(
            pokeName = "bulbsaaur",
            pokeImage = ""
        )
    }
}