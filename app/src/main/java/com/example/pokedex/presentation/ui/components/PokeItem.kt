package com.example.pokedex.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.pokedex.presentation.getColor
import com.example.pokedex.presentation.ui.theme.PokeDexTheme
import com.example.pokedex.presentation.ui.theme.Poppins
import com.example.pokedex.presentation.ui.theme.Shapes

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokeItem(
    name: String,
    pokeNumber: String,
    imageUrl: String,
    itemColor: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .size(104.dp, 112.dp),
        shape = Shapes.medium,
        border = BorderStroke(1.dp, getColor(itemColor))
    ) {
        Box {
            Text(
                text = pokeNumber,
                color = getColor(itemColor),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 4.dp, end = 8.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 10.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                )
            )
            GlideImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(72.dp)
                    .padding(bottom = 8.dp)
            )
            Surface(
                color = getColor(itemColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                ,
            ){
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 16.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokeItemPreview() {
    PokeDexTheme {
        PokeItem("Bulbasaur", "#011", "", "#74CB48")
    }
}