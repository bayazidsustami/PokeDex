package com.example.pokedex.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.pokedex.R
import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import com.example.pokedex.presentation.UiEvent
import com.example.pokedex.presentation.colorHex
import com.example.pokedex.presentation.getColor
import com.example.pokedex.presentation.getColorFromTypes
import com.example.pokedex.presentation.ui.components.PokeAttribute
import com.example.pokedex.presentation.ui.components.PokeStats
import com.example.pokedex.presentation.ui.theme.DarkGray
import com.example.pokedex.presentation.ui.theme.PokeDexTheme
import com.example.pokedex.presentation.ui.theme.Poppins
import com.example.pokedex.presentation.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    itemColor: String,
    pokemonName: String,
    pokeNumber: String,
    pokeImage: String
) {
    val rawId = pokeNumber.removePrefix("#").toInt()
    val scrollState = rememberScrollState()

    viewModel.pokemonDetail.collectAsState().value.let { uiEvent ->
        when(uiEvent){
            is UiEvent.Loading -> {
                viewModel.fetchDetails(rawId.toString())
            }
            is UiEvent.Success -> {
                DetailContent(
                    modifier = modifier,
                    itemColor = itemColor,
                    onBackPressed = onBackPressed,
                    pokeNumber = pokeNumber,
                    pokeImageUrl = pokeImage,
                    pokeName = pokemonName,
                    pokemonDetail = uiEvent.data,
                    scrollState = scrollState
                )
            }
            is UiEvent.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    itemColor: String,
    pokeName: String,
    pokeNumber: String,
    pokeImageUrl: String,
    onBackPressed: () -> Unit,
    pokemonDetail: PokemonDetailEntity,
    scrollState: ScrollState,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(getColor(colorHex(itemColor)))
    ){
        Image(
            painter = painterResource(R.drawable.ic_poke_transparent),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 8.dp)
        )
        DetailTopBar(
            pokeName = pokeName,
            pokeNumber = pokeNumber,
            onBackPressed = onBackPressed
        )
        DetailPokemon(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            pokeName = pokeName,
            pokemonDetail = pokemonDetail,
            pokeImageUrl = pokeImageUrl,
            itemColor = itemColor,
            scrollState = scrollState
        )
    }
}

@Composable
fun DetailTopBar(
    modifier: Modifier = Modifier,
    pokeName: String,
    pokeNumber: String,
    onBackPressed: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable { onBackPressed() }
        )
        Text(
            text = pokeName,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(start = 20.dp),
            color = Color.White,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
        )
        Text(
            text = pokeNumber,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            fontSize = 12.sp,
            lineHeight = 32.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailPokemon(
    modifier: Modifier = Modifier,
    pokemonDetail: PokemonDetailEntity,
    pokeImageUrl: String,
    scrollState: ScrollState,
    pokeName: String,
    itemColor: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(4.dp),
    ) {
        GlideImage(
            model = pokeImageUrl,
            contentDescription = pokeName,
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.CenterHorizontally)
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .sizeIn(minHeight = 412.dp)
                .background(Color.White)
                .padding(top = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.about),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 14.sp,
                lineHeight = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                color = getColor(colorHex(itemColor))
            )
            TypeSection(
                modifier = Modifier
                    .fillMaxWidth(),
                types = pokemonDetail.types.map { it.type.name }
            )
            PokeAttribute(
                weight = pokemonDetail.getWeightString() ,
                height = pokemonDetail.getHeightString(),
                moves = pokemonDetail.move,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredWidth(245.dp)
                    .padding(top = 16.dp)
            )
            Text(
                text = pokemonDetail.ability,
                fontWeight = FontWeight.Normal,
                fontFamily = Poppins,
                fontSize = 10.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Justify,
                color = DarkGray,
                modifier = Modifier
                    .padding(top = 16.dp, start = 20.dp, end = 20.dp)
            )
            Text(
                text = stringResource(R.string.base_stats),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                fontSize = 14.sp,
                lineHeight = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                color = getColor(colorHex(itemColor))
            )
            PokeStats(
                hp = pokemonDetail.hp,
                atk = pokemonDetail.attack,
                def = pokemonDetail.defense,
                satk = pokemonDetail.specialAttack,
                sdef = pokemonDetail.specialDefense,
                spd = pokemonDetail.speed,
                type = itemColor,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TypeSection(
    modifier: Modifier = Modifier,
    types: List<String>
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        types.forEach {
            Chip(
                modifier = Modifier
                    .padding(start = 2.dp, end = 2.dp),
                colors = ChipDefaults.chipColors(
                    backgroundColor = getColorFromTypes(it)
                ),
                shape = RoundedCornerShape(18.dp),
                onClick = {},
                content = {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Poppins,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        lineHeight = 16.sp,
                        color = Color.White
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    PokeDexTheme {
        TypeSection(types = listOf("flying", "fire"))
    }
}