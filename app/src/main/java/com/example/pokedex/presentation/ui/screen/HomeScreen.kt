package com.example.pokedex.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedex.R
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.presentation.UiEvent
import com.example.pokedex.presentation.colorHex
import com.example.pokedex.presentation.ui.components.PokeItem
import com.example.pokedex.presentation.ui.components.SearchTextField
import com.example.pokedex.presentation.ui.theme.Background
import com.example.pokedex.presentation.ui.theme.PokeDexTheme
import com.example.pokedex.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClicked: (PokemonEntity) -> Unit
) {

    var queryPoke by remember { mutableStateOf("") }
    var isFocus by remember { mutableStateOf(false) }
    val focusRequester by remember { mutableStateOf(FocusRequester.Default) }
    val focusManager = LocalFocusManager.current

    viewModel.pokeList.collectAsState(initial = UiEvent.Loading).value.let { uiEvent ->
        when (uiEvent) {
            is UiEvent.Loading -> {
                viewModel.getPokemonList()
            }
            is UiEvent.Success -> {
                HomeContent(
                    modifier = modifier,
                    listPoke = uiEvent.data,
                    queryPoke = queryPoke,
                    isFocus = isFocus,
                    focusRequester = focusRequester,
                    onFocusChanged = {
                        if (isFocus != it.isFocused) {
                            isFocus = it.isFocused
                        }
                    },
                    onValueChanged = {
                        queryPoke = it
                        viewModel.getPokemonList(it)
                    },
                    onCleared = {
                        isFocus = false
                        queryPoke = ""
                        focusManager.clearFocus()
                        viewModel.getPokemonList()
                    },
                    onItemClicked = onItemClicked
                )
            }
            is UiEvent.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    queryPoke: String,
    focusRequester: FocusRequester,
    isFocus: Boolean,
    listPoke: List<PokemonEntity>,
    onValueChanged: (String) -> Unit,
    onCleared: () -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    onItemClicked: (PokemonEntity) -> Unit,
) {

    Column(
        modifier = modifier
            .background(color = Background)
    ) {
        HomeTopBar()
        SearchTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            value = queryPoke,
            focusRequester = focusRequester,
            isFocus = isFocus,
            onFocusChanged = onFocusChanged,
            onValueChange = onValueChanged,
            onCleared = onCleared
        )
        HomeListPoke(listPoke, onItemClicked = onItemClicked)
    }
}

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 32.dp),
        backgroundColor = Background,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_pokeball),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
            )
            Image(
                painter = painterResource(R.drawable.poke_dex),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(width = 132.dp, height = 32.dp)
                    .padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun HomeListPoke(
    listPoke: List<PokemonEntity>,
    modifier: Modifier = Modifier,
    onItemClicked: (PokemonEntity) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier
            .padding(16.dp),
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(listPoke, key = { it.pokeNumber }) { item ->
            PokeItem(
                name = item.pokeName,
                pokeNumber = item.pokeNumber,
                imageUrl = item.imageUrl,
                itemColor = colorHex(item.colorTypes),
                modifier = Modifier.clickable {
                    onItemClicked(item)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PokeDexTheme {
        HomeScreen(onItemClicked = {})
    }
}