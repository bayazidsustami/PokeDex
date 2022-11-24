package com.example.pokedex.data.datasource.local.di

import android.content.Context
import androidx.room.Room
import com.example.pokedex.common.Constant
import com.example.pokedex.data.datasource.local.room.AppDatabase
import com.example.pokedex.data.datasource.local.room.PokeDetailTypeConverter
import com.example.pokedex.data.datasource.local.room.dao.PokemonDao
import com.example.pokedex.data.datasource.local.room.dao.PokemonDetailDao
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        typeConverter: PokeDetailTypeConverter,
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constant.Values.DATABASE_NAME
        ).addTypeConverter(typeConverter).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun providePokemonDao(database: AppDatabase): PokemonDao {
        return database.pokemonDao()
    }

    @Provides
    fun providePokemonDetailDao(database: AppDatabase): PokemonDetailDao {
        return database.pokemonDetailDao()
    }
}