package com.example.pokedex.presentation

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.compose.ui.graphics.Color as ComposeColor
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.google.android.material.progressindicator.LinearProgressIndicator

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun LinearProgressIndicator.setColor(types: String) {
    setIndicatorColor(Color.parseColor(colorHex(types)))
    trackColor = Color.parseColor(colorHex20Opacity(types))
    trackCornerRadius = 8
}

fun Int.toFormattedString(): String {
    return String.format("#%03d", this)
}

fun getColor(color: String): ComposeColor {
    return ComposeColor(Color.parseColor(color))
}

fun colorHex(types: String): String {
    return when(types) {
        "type_grass" -> "#74CB48"
        "type_fire" -> "#F57D31"
        "type_water" -> "#6493EB"
        "type_bug" -> "#A7B723"
        "type_electric" -> "#F9CF30"
        "type_gosh" -> "#70559B"
        "type_normal" -> "#AAA67F"
        "type_psychic" -> "#FB5584"
        "type_steel"-> "#B7B9D0"
        else -> "#74CB48"
    }
}

fun colorHex20Opacity(types: String): String {
    return when(types) {
        "type_grass" -> "#3374CB48"
        "type_fire" -> "#33F57D31"
        "type_water" -> "#336493EB"
        "type_bug" -> "#33A7B723"
        "type_electric" -> "#33F9CF30"
        "type_gosh" -> "#3370559B"
        "type_normal" -> "#33AAA67F"
        "type_psychic" -> "#33FB5584"
        "type_steel"-> "#33B7B9D0"
        else -> "#3374CB48"
    }
}

fun getColorRes(types: String) : Int {
    return when(types) {
        "type_grass" -> R.color.type_grass
        "type_fire" -> R.color.type_fire
        "type_water" -> R.color.type_water
        "type_bug" -> R.color.type_bug
        "type_electric" -> R.color.type_electric
        "type_gosh" -> R.color.type_gosh
        "type_normal" -> R.color.type_normal
        "type_psychic" -> R.color.type_psychic
        "type_steel"-> R.color.type_steel
        else -> R.color.type_grass
    }
}

fun getColorFromDetailTypes(types: String) : Int {
    return when(types) {
        "grass" -> R.color.type_grass
        "fire" -> R.color.type_fire
        "water" -> R.color.type_water
        "bug" -> R.color.type_bug
        "electric" -> R.color.type_electric
        "gosh" -> R.color.type_gosh
        "normal" -> R.color.type_normal
        "psychic" -> R.color.type_psychic
        "steel"-> R.color.type_steel
        "poison" -> R.color.type_poison
        "rock" -> R.color.type_rock
        "flying" -> R.color.type_flying
        else -> R.color.type_grass
    }
}


val listPokeColors: List<String>
    get() = listOf(
        "type_grass",
        "type_fire",
        "type_water",
        "type_bug",
        "type_electric",
        "type_gosh",
        "type_normal",
        "type_psychic",
        "type_steel",
    )