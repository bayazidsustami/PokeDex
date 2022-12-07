package com.example.pokedex.presentation.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.R
import com.example.pokedex.presentation.ui.theme.DarkGray
import com.example.pokedex.presentation.ui.theme.LightGray
import com.example.pokedex.presentation.ui.theme.MediumGray
import com.example.pokedex.presentation.ui.theme.PokeDexTheme
import com.example.pokedex.presentation.ui.theme.Poppins

@Composable
fun PokeAttribute(
    modifier: Modifier = Modifier,
    weight: String,
    height: String,
    moves: String
){
    Row(
        modifier = modifier
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PokeAttributeItem(
            icon = R.drawable.ic_weight,
            attributeName = stringResource(R.string.weight),
            attributeValue = weight,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Divider(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight(),
            color = LightGray
        )
        PokeAttributeItem(
            icon = R.drawable.ic_height,
            attributeName = stringResource(R.string.height),
            attributeValue = height,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )
        Divider(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight(),
            color = LightGray
        )
        PokeAttributeItem(
            icon = null,
            attributeName = stringResource(R.string.moves),
            attributeValue = moves,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
fun PokeAttributeItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int?,
    attributeName: String,
    attributeValue: String
){
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            icon?.let {
                Icon(
                    painter = painterResource(it),
                    contentDescription = null
                )
            }
            Text(
                text = attributeValue,
                fontSize = 10.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                lineHeight = 16.sp,
                textAlign = TextAlign.Justify,
                color = DarkGray,
                modifier = Modifier
                    .requiredSizeIn(minWidth = 16.dp)
                    .align(Alignment.CenterVertically)
                    .padding(start = if (icon == null) 0.dp else 8.dp)
            )
        }
        Text(
            text = attributeName,
            fontSize = 8.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            lineHeight = 8.sp,
            textAlign = TextAlign.Center,
            color = MediumGray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokeAttributePreview() {
    PokeDexTheme {
        PokeAttribute(
            weight = "9.0kg",
            height = "0.5cm",
            moves = "Torrent Rain-Dish"
        )
    }
}