package com.example.pokedex.data.datasource.remote.di

import com.example.pokedex.data.datasource.remote.networking.ApiService
import com.example.pokedex.data.datasource.remote.networking.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): ApiService {
        return RetrofitBuilder.get()
    }
}