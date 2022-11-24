package com.example.pokedex.data.datasource.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokedex.common.Constant
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constant.Values.POKEMON_TABLE)
data class PokemonEntity(
    @PrimaryKey
    @ColumnInfo(name = "poke_number")
    val pokeNumber: String,
    @ColumnInfo(name = "poke_name")
    val pokeName: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
): Parcelable
