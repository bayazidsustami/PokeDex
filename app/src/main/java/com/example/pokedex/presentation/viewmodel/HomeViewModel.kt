package com.example.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.common.PokeSort
import com.example.pokedex.common.Resource
import com.example.pokedex.common.dispatchers.di.qulifiers.MainDispatcher
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.domain.PokemonHomeUseCase
import com.example.pokedex.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: PokemonHomeUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _pokeList = MutableStateFlow<UiEvent<List<PokemonEntity>>>(UiEvent.Loading)
    val pokeList: StateFlow<UiEvent<List<PokemonEntity>>> get() = _pokeList

    fun getPokemonList(name: String = "", sortBy: PokeSort = PokeSort.NUMBER) {
        viewModelScope.launch(dispatcher) {
            homeUseCase.getListPokemon(name, sortBy).collect {
                when(it) {
                    is Resource.Loading -> {
                        _pokeList.value = UiEvent.Loading
                    }
                    is Resource.Success -> {
                        _pokeList.value = UiEvent.Success(it.data)
                    }
                    is Resource.Error -> {
                        _pokeList.value = UiEvent.Error(it.message, it.errorCode)
                    }
                }
            }
        }
    }

}