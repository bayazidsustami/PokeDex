package com.example.pokedex.domain.di

import com.example.pokedex.domain.PokemonDetailUseCase
import com.example.pokedex.domain.PokemonDetailUseCaseImpl
import com.example.pokedex.domain.PokemonHomeUseCase
import com.example.pokedex.domain.PokemonHomeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindPokeHomeUseCase(homeUseCase: PokemonHomeUseCaseImpl): PokemonHomeUseCase

    @Binds
    abstract fun bindPokeDetailUseCase(detailUseCase: PokemonDetailUseCaseImpl): PokemonDetailUseCase
}