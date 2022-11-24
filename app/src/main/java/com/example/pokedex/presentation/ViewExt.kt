package com.example.pokedex.presentation

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.pokedex.R

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