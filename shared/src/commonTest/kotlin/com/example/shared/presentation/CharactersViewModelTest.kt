package com.example.shared.presentation

import com.example.shared.data.commons.FakeCharactersRepository
import com.example.shared.domain.entity.CharacterEntity
import com.example.shared.domain.entity.CharactersEntity
import com.example.shared.domain.entity.CharactersResult
import com.example.shared.domain.entity.Gender
import com.example.shared.domain.entity.SPECIES
import com.example.shared.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersViewModelTest : KoinTest {

    private val viewModel: CharactersViewModel by inject()

    private val characterRepository = FakeCharactersRepository()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                module {
                    single { GetCharactersUseCase(characterRepository) }
                    single { CharactersViewModel() }
                }
            )
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testFechCharactersSuccess() = runTest {
        val character = CharacterEntity(
            id = 1,
            name = "Rick Sanchez",
            image = "some_url",
            species = SPECIES.HUMAN,
            gender = Gender.MALE
        )
        characterRepository.setCharacters(
            CharactersEntity(
                characters = listOf(character),
                next = "next",
                prev = null,
                count = 1
            )
        )

        viewModel.getCharacters()

        assertTrue(viewModel.charactersState.value is CharactersResult.Loading)

        runCurrent()
        val result = viewModel.charactersState.value as? CharactersResult.Success
        assertNotNull(result)
        val response = result.characterResponse
        assertTrue(response.characters.isNotEmpty())
        assertTrue(response.characters.size == 1)
        assertTrue(response.characters[0].id == 1)
        assertTrue(response.characters[0].name == "Rick Sanchez")
        assertTrue(response.characters[0].image == "some_url")
        assertEquals(response.characters[0].species, SPECIES.HUMAN)
        assertEquals(response.characters[0].gender, Gender.MALE)
    }

    @Test
    fun testFechCharactersError() = runTest {
        characterRepository.setShouldReturnError(true)
        viewModel.getCharacters()

        assertTrue(viewModel.charactersState.value is CharactersResult.Loading)

        runCurrent()
        val result = viewModel.charactersState.value as? CharactersResult.Error
        assertNotNull(result)
    }
}