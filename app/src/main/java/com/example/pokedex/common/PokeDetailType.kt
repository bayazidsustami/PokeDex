package com.example.pokedex.common

import android.os.Bundle
import androidx.navigation.NavType
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.google.gson.Gson

class PokeDetailType: NavType<PokemonEntity>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): PokemonEntity? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): PokemonEntity {
        return Gson().fromJson(value, PokemonEntity::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: PokemonEntity) {
        bundle.putParcelable(key, value)
    }
}