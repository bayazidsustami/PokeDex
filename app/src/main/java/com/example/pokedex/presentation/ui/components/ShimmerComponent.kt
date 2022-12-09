package com.example.pokedex.presentation.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedex.presentation.ui.theme.PokeDexTheme
import com.example.pokedex.presentation.ui.theme.ShimmerColorShades

@Composable
fun ShimmerComponent(
    modifier: Modifier = Modifier,
    transition: InfiniteTransition = rememberInfiniteTransition(),
) {
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 1200,
                easing = FastOutSlowInEasing,
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f,10f),
        end = Offset(translateAnimation, translateAnimation)
    )

    Spacer(
        modifier = modifier
            .background(brush = brush)
    )
}

@Preview(showBackground = true)
@Composable
fun ShimmerPreview() {
    PokeDexTheme {
        ShimmerComponent(
            modifier = Modifier
                .size(250.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}