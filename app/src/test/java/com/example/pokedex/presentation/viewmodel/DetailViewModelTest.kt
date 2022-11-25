package com.example.pokedex.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokedex.CoroutineTestRule
import com.example.pokedex.common.Resource
import com.example.pokedex.common.dispatchers.TestDispatchers
import com.example.pokedex.data.datasource.local.entity.PokemonDetailEntity
import com.example.pokedex.data.datasource.local.entity.PokemonEntity
import com.example.pokedex.data.datasource.remote.response.PokemonDetail
import com.example.pokedex.domain.PokemonDetailUseCase
import com.example.pokedex.presentation.UiEvent
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestDispatchers()

    private lateinit var viewModel: DetailViewModel

    private val detailUseCase: PokemonDetailUseCase = mockk()

    @Before
    fun setup() {
        viewModel = DetailViewModel(detailUseCase, dispatcher.main())
    }

    @Test
    fun `fetch detail pokemon by id should show loading`() = coroutineRule.runBlockingTest {
        val expected = flow<Resource<PokemonDetailEntity>> {
            emit(Resource.Loading)
        }

        coEvery { detailUseCase.getPokemon(any()) } returns expected

        viewModel.fetchDetails("")

        val actual = viewModel.pokemonDetail.value
        assertNotNull(actual)
        assertTrue(actual is UiEvent.Loading)

        coVerify { detailUseCase.getPokemon(any()) }

    }

    @Test
    fun `fetch detail pokemon by id should be success and return data`() = coroutineRule.runBlockingTest {
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

        coEvery { detailUseCase.getPokemon(any()) } returns expected

        viewModel.fetchDetails("")

        val actual = viewModel.pokemonDetail.value
        assertNotNull(actual)
        assertTrue(actual is UiEvent.Success)

        val data = (actual as UiEvent.Success).data
        assertEquals(expectedData, data)

        coVerify { detailUseCase.getPokemon(any()) }
    }

    @Test
    fun `fetch detail pokemon by id should be fail and throw an error`() = coroutineRule.runBlockingTest {
        val error = Resource.Error("failed to fetch", 404)
        val expected = flow<Resource<PokemonDetailEntity>> { emit(error) }

        coEvery { detailUseCase.getPokemon(any()) } returns expected

        viewModel.fetchDetails("")

        val actual = viewModel.pokemonDetail.value
        assertNotNull(actual)
        assertTrue(actual is UiEvent.Error)
        assertFalse(actual is UiEvent.Success)
        val data = actual as UiEvent.Error
        assertEquals(error.message, data.message)
        assertEquals(error.errorCode, data.errorCode)

        coVerify { detailUseCase.getPokemon(any()) }
    }

    @Test
    fun `fetch pokemon evolutions should be show loading`() = coroutineRule.runBlockingTest {
        val expected = flow<Resource<List<PokemonEntity>>> { emit(Resource.Loading) }

        coEvery { detailUseCase.getPokemonEvolution(any()) } returns expected

        viewModel.fetchPokeEvolutions("")

        val actual = viewModel.pokemonEvolution.value
        assertNotNull(actual)
        assertTrue(actual is UiEvent.Loading)

        coVerify { detailUseCase.getPokemonEvolution(any()) }
    }

    @Test
    fun `fetch pokemon evolutions should be success and return data`() = coroutineRule.runBlockingTest {
        val items = listOf(
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
            PokemonEntity("#001", "bulbasurus", "https://exapmple.com", "https://exapmple.com", "type_grass"),
        )

        val expected = flow {
            emit(Resource.Success(items))
        }

        coEvery { detailUseCase.getPokemonEvolution(any()) } returns expected

        viewModel.fetchPokeEvolutions("")

        val actual = viewModel.pokemonEvolution.value
        assertNotNull(actual)
        assertTrue(actual is UiEvent.Success)
        assertFalse(actual is UiEvent.Error)
        val data = (actual as UiEvent.Success).data
        assertEquals(items.size, data.size)
        assertEquals(items[0].pokeName, data[0].pokeName)
        assertEquals(items[0].pokeNumber, data[0].pokeNumber)
        assertEquals(items[0].imageUrl, data[0].imageUrl)
        assertEquals(items[0].url, data[0].url)
        assertEquals(items[0].colorTypes, data[0].colorTypes)

        coVerify { detailUseCase.getPokemonEvolution(any()) }
    }

    @Test
    fun `fetch list pokemon evolution should be failed and thro an error`() = coroutineRule.runBlockingTest {
        val error = Resource.Error("failed to fetch", 404)
        val expected = flow<Resource<List<PokemonEntity>>> {
            emit(error)
        }

        coEvery { detailUseCase.getPokemonEvolution(any()) } returns expected

        viewModel.fetchPokeEvolutions("")

        val actual = viewModel.pokemonEvolution.value
        assertNotNull(actual)
        assertTrue(actual is UiEvent.Error)
        assertFalse(actual is UiEvent.Success)
        val data = actual as UiEvent.Error
        assertEquals(error.message, data.message)
        assertEquals(error.errorCode, data.errorCode)

        coVerify { detailUseCase.getPokemonEvolution(any()) }
    }
}