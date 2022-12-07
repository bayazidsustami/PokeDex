package com.example.pokedex.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.R
import com.example.pokedex.presentation.colorHex
import com.example.pokedex.presentation.colorHex20Opacity
import com.example.pokedex.presentation.getColor
import com.example.pokedex.presentation.ui.theme.DarkGray
import com.example.pokedex.presentation.ui.theme.PokeDexTheme
import com.example.pokedex.presentation.ui.theme.Poppins

@Composable
fun PokeStats(
    modifier: Modifier = Modifier,
    hp: Int,
    atk: Int,
    def: Int,
    satk: Int,
    sdef: Int,
    spd: Int,
    type: String
){
    Row(
        modifier = modifier
            .height(105.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            PokeStatsProgress(
                value = hp,
                type = type,
                label = stringResource(R.string.hp),
            )
            PokeStatsProgress(
                value = atk,
                type = type,
                label = stringResource(R.string.atk),
            )
            PokeStatsProgress(
                value = def,
                type = type,
                label = stringResource(R.string.def),
            )
            PokeStatsProgress(
                value = satk,
                type = type,
                label = stringResource(R.string.satk),
            )
            PokeStatsProgress(
                value = sdef,
                type = type,
                label = stringResource(R.string.sdef),
            )
            PokeStatsProgress(
                value = spd,
                type = type,
                label = stringResource(R.string.spd)
            )
        }
    }
}

@Composable
private fun PokeStatsProgress(
    modifier: Modifier = Modifier,
    label: String,
    value: Int,
    type: String
) {
    Row(
        modifier = modifier
            .height(16.dp)
    ) {
        TextStatsLabel(
            text = label,
            color = getColor(colorHex(type)),
        )
        Divider(
            modifier = Modifier
                .padding(start = 12.dp)
                .fillMaxHeight()
                .width(1.dp),
            color = Color.LightGray,
        )
        Text(
            text = String.format("%03d", value),
            modifier = modifier
                .padding(start = 12.dp),
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 16.sp,
            color = DarkGray
        )
        LinearProgressIndicator(
            modifier = Modifier
                .height(4.dp)
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
                .clip(RoundedCornerShape(4.dp)),
            progress = (value/100f),
            backgroundColor = getColor(colorHex20Opacity(type)),
            color = getColor(colorHex(type))
        )
    }
}

@Composable
private fun TextStatsLabel(
    modifier: Modifier = Modifier,
    text: String,
    color: Color
) {
    Text(
        text = text,
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.End,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        modifier = modifier
            .requiredWidth(28.dp),
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun PokeStatsPreview(){
    PokeDexTheme {
        PokeStats(
            hp = 44,
            atk = 48,
            def = 65,
            satk = 50,
            sdef = 50,
            spd = 64,
            type = "type_grass"
        )
    }
}