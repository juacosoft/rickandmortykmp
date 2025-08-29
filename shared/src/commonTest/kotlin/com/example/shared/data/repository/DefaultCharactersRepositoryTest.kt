package com.example.shared.data.repository

import com.example.shared.data.commons.FakeCharactersRepository
import com.example.shared.domain.entity.CharactersResult
import com.example.shared.domain.repository.CharactersRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class DefaultCharactersRepositoryTest {

    private val repository: CharactersRepository = FakeCharactersRepository()

    @Test
    fun testFetchCharactersSuccess() = runTest {
        val result = repository.fetchCharacters()
        assertTrue(result is CharactersResult.Success)

    }

    @Test
    fun testFetchCharactersError() = runTest {
        (repository as FakeCharactersRepository).setShouldReturnError(true)
        val result = repository.fetchCharacters()
        assertTrue(result is CharactersResult.Error)
    }
}