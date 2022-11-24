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


val listPokeColors: List<Int>
    get() = listOf(
        R.color.type_grass,
        R.color.type_fire,
        R.color.type_water,
        R.color.type_bug,
        R.color.type_electric,
        R.color.type_gosh,
        R.color.type_normal,
        R.color.type_psychic,
        R.color.type_steel,
    )