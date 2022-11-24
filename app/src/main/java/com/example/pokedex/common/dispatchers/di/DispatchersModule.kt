package com.example.pokedex.common.dispatchers.di

import com.example.pokedex.common.dispatchers.AppDispatchers
import com.example.pokedex.common.dispatchers.DispatcherProvider
import com.example.pokedex.common.dispatchers.di.qulifiers.IODispatcher
import com.example.pokedex.common.dispatchers.di.qulifiers.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    fun provideAppDispatcher(): DispatcherProvider = AppDispatchers()

    @MainDispatcher
    @Provides
    fun provideMainDispatcher(dispatcherProvider: DispatcherProvider): CoroutineDispatcher {
        return dispatcherProvider.main()
    }


    @IODispatcher
    @Provides
    fun provideIODispatcher(dispatcherProvider: DispatcherProvider): CoroutineDispatcher {
        return dispatcherProvider.io()
    }


}