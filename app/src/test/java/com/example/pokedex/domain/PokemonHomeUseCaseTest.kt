package com.example.pokedex.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokedex.CoroutineTestRule
import com.example.pokedex.common.PokeSort
import com.example.pokedex.common.Resource
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.repository.PokemonHomeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonHomeUseCaseTest {
    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var pokemonHomeUseCase: PokemonHomeUseCase

    private val pokemonHomeRepository: PokemonHomeRepository = mockk()

    @Before
    fun setup() {
        pokemonHomeUseCase = PokemonHomeUseCaseImpl(pokemonHomeRepository)
    }

    @Test
    fun `get pokemon list should return loading`() = coroutineRule.runBlockingTest {
        val expected = flow<Resource<List<PokemonEntity>>> { emit(Resource.Loading) }

        coEvery { pokemonHomeRepository.getListPokemon(any(), any()) } returns expected

        val actual = pokemonHomeUseCase.getListPokemon("", PokeSort.NUMBER)
        assertTrue(expected == actual)

        coVerify { pokemonHomeRepository.getListPokemon(any(), any()) }
    }

    @Test
    fun `get pokemon list should return success`() = coroutineRule.runBlockingTest {
        val items = listOf(
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
        )

        val expected = flow { emit(Resource.Success(items)) }

        coEvery { pokemonHomeRepository.getListPokemon(any(), any()) } returns expected

        val actual = pokemonHomeUseCase.getListPokemon("", PokeSort.NUMBER)
        assertTrue(expected == actual)

        coVerify { pokemonHomeRepository.getListPokemon(any(), any()) }
    }

    @Test
    fun `get pokemon list should return error`() = coroutineRule.runBlockingTest {
        val error = Resource.Error("failed to fetch", 404)
        val expected = flow<Resource<List<PokemonEntity>>> {
            emit(error)
        }

        coEvery { pokemonHomeRepository.getListPokemon(any(), any()) } returns expected

        val actual = pokemonHomeUseCase.getListPokemon("", PokeSort.NUMBER)
        assertTrue(expected == actual)

        coVerify { pokemonHomeRepository.getListPokemon(any(), any()) }
    }
}