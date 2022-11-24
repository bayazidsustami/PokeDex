package com.example.pokedex.data.repository.di

import com.example.pokedex.data.datasource.local.di.LocalDataSourceModule
import com.example.pokedex.data.datasource.remote.di.NetworkModule
import com.example.pokedex.data.repository.PokemonDetailRepository
import com.example.pokedex.data.repository.PokemonDetailRepositoryImpl
import com.example.pokedex.data.repository.PokemonHomeRepository
import com.example.pokedex.data.repository.PokemonHomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, LocalDataSourceModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsHomeRepository(homeRepository: PokemonHomeRepositoryImpl): PokemonHomeRepository

    @Binds
    abstract fun bindsDetailRepository(detailRepository: PokemonDetailRepositoryImpl): PokemonDetailRepository
}