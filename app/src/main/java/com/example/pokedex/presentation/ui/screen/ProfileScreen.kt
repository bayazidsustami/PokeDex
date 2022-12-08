package com.example.pokedex.presentation.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.R
import com.example.pokedex.presentation.ui.theme.Background
import com.example.pokedex.presentation.ui.theme.DarkGray
import com.example.pokedex.presentation.ui.theme.PokeDexTheme
import com.example.pokedex.presentation.ui.theme.Poppins


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
) {

    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            backgroundColor = Background,
            elevation = 0.dp
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .clickable { onNavigateBack() }
                    .padding(start = 16.dp)
            )
            Text(
                text = stringResource(R.string.profile),
                color = DarkGray,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 18.sp,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
        Spacer(Modifier.padding(16.dp))
        Image(
            painter = painterResource(R.drawable.img_profile),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .border(
                    BorderStroke(4.dp, rainbowColorsBrush),
                    CircleShape
                )
                .padding(4.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.padding(16.dp))
        Text(
            text = stringResource(R.string.email),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Poppins,
            lineHeight = 16.sp,
            color = DarkGray
        )
        Spacer(Modifier.padding(8.dp))
        Text(
            text = stringResource(R.string.name),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Poppins,
            lineHeight = 16.sp,
            color = DarkGray
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PokeDexTheme {
        ProfileScreen(onNavigateBack = {})
    }
}