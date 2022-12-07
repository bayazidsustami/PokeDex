package com.example.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.common.Resource
import com.example.pokedex.common.dispatchers.di.qulifiers.MainDispatcher
import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.domain.PokemonDetailUseCase
import com.example.pokedex.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCase: PokemonDetailUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _pokemonEvolutions = MutableStateFlow<UiEvent<List<PokemonEntity>>>(UiEvent.Loading)
    val pokemonEvolution: StateFlow<UiEvent<List<PokemonEntity>>> get() = _pokemonEvolutions

    private val _pokemonDetail = MutableStateFlow<UiEvent<PokemonDetailEntity>>(UiEvent.Loading)
    val pokemonDetail: StateFlow<UiEvent<PokemonDetailEntity>> get() = _pokemonDetail

    fun fetchDetails(id: String) {
        viewModelScope.launch(dispatcher) {
            detailUseCase.getPokemon(id).collect {
                when(it) {
                    is Resource.Loading -> {
                        _pokemonDetail.value = UiEvent.Loading
                    }
                    is Resource.Success -> {
                        _pokemonDetail.value = UiEvent.Success(it.data)
                    }
                    is Resource.Error -> {
                        _pokemonDetail.value = UiEvent.Error(it.message, it.errorCode)
                    }
                }
            }
        }
    }

    fun fetchPokeEvolutions(id: String) {
        viewModelScope.launch(dispatcher) {
            detailUseCase.getPokemonEvolution(id).collect {
                when(it) {
                    is Resource.Loading -> {
                        _pokemonEvolutions.value = UiEvent.Loading
                    }
                    is Resource.Success -> {
                        _pokemonEvolutions.value = UiEvent.Success(it.data)
                    }
                    is Resource.Error -> {
                        _pokemonEvolutions.value = UiEvent.Error(it.message, it.errorCode)
                    }
                }
            }
        }
    }

}