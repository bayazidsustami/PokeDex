package com.example.pokedex.data.datasource.local.di

import android.content.Context
import androidx.room.Room
import com.example.pokedex.common.Constant
import com.example.pokedex.data.datasource.local.room.AppDatabase
import com.example.pokedex.data.datasource.local.room.dao.PokemonDao
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constant.Values.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providePokemonDao(database: AppDatabase): PokemonDao {
        return database.pokemonDao()
    }
}