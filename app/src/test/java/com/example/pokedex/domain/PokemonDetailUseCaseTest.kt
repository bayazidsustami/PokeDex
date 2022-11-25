package com.example.pokedex.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokedex.CoroutineTestRule
import com.example.pokedex.common.Resource
import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.datasource.remote.response.PokemonDetail
import com.example.pokedex.data.repository.PokemonDetailRepository
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
class PokemonDetailUseCaseTest {
    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var pokemonDetailUseCase: PokemonDetailUseCase

    private val pokemonDetailRepository: PokemonDetailRepository = mockk()

    @Before
    fun setup(){
        pokemonDetailUseCase = PokemonDetailUseCaseImpl(pokemonDetailRepository)
    }

    @Test
    fun `get pokemon by id should return loading`() = coroutineRule.runBlockingTest {
        val expected = flow<Resource<PokemonDetailEntity>> {
            emit(Resource.Loading)
        }

        coEvery { pokemonDetailRepository.getPokemon(any()) } returns expected

        val data = pokemonDetailUseCase.getPokemon("")

        assertTrue(expected == data)

        coVerify { pokemonDetailRepository.getPokemon(any()) }
    }

    @Test
    fun `get pokemon by id should return success`() = coroutineRule.runBlockingTest {
        val expectedData = PokemonDetailEntity(
            1,
            "bulbasaur",
            65,
            34,
            "run",
            listOf(PokemonDetail.TypeResponse()),
            32,
            54,
            66,43,
            34,53,"grass"
        )
        val expected = flow { emit(Resource.Success(expectedData)) }

        coEvery { pokemonDetailRepository.getPokemon(any()) } returns expected

        val actualData = pokemonDetailUseCase.getPokemon("")

        assertTrue(expected == actualData)

        coVerify { pokemonDetailRepository.getPokemon(any()) }
    }

    @Test
    fun `get pokemon by id should return error`() = coroutineRule.runBlockingTest {
        val error = Resource.Error("failed to fetch", 404)
        val expected = flow<Resource<PokemonDetailEntity>> { emit(error) }

        coEvery { pokemonDetailRepository.getPokemon(any()) } returns expected

        val actualData = pokemonDetailUseCase.getPokemon("")

        assertTrue(expected == actualData)

        coVerify { pokemonDetailRepository.getPokemon(any()) }
    }

    @Test
    fun `get pokemon evolution should return loading`() = coroutineRule.runBlockingTest {
        val expected = flow<Resource<List<PokemonEntity>>> { emit(Resource.Loading) }

        coEvery { pokemonDetailRepository.getPokemonEvolution(any()) } returns expected

        val actualData = pokemonDetailUseCase.getPokemonEvolution("")

        assertTrue(expected == actualData)

        coVerify { pokemonDetailRepository.getPokemonEvolution(any()) }
    }

    @Test
    fun `get pokemon evolution should return success`() = coroutineRule.runBlockingTest {
        val items = listOf(
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
        )

        val expected = flow { emit(Resource.Success(items)) }

        coEvery { pokemonDetailRepository.getPokemonEvolution(any()) } returns expected

        val actualData = pokemonDetailUseCase.getPokemonEvolution("")

        assertTrue(expected == actualData)

        coVerify { pokemonDetailRepository.getPokemonEvolution(any()) }
    }

    @Test
    fun `get pokemon evolution should return error`() = coroutineRule.runBlockingTest {
        val error = Resource.Error("failed to fetch", 404)
        val expected = flow<Resource<List<PokemonEntity>>> {
            emit(error)
        }

        coEvery { pokemonDetailRepository.getPokemonEvolution(any()) } returns expected

        val actualData = pokemonDetailUseCase.getPokemonEvolution("")

        assertTrue(expected == actualData)

        coVerify { pokemonDetailRepository.getPokemonEvolution(any()) }
    }
}