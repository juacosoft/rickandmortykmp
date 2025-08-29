package com.example.shared.data.client

import com.example.shared.data.BasicResultModel
import com.example.shared.data.commons.mockClient
import com.example.shared.data.commons.mockEngineError
import com.example.shared.data.commons.mockEngineSuccess
import com.example.shared.data.model.CharactersRespnse
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class GetCharactersApiTest {

    private lateinit var getCharactersApi: GetCharactersApi

    @Test
    fun testGetCharractersApiSuccess() = runTest {
        getCharactersApi = GetCharactersApi(mockClient(mockEngineSuccess))

        val response = getCharactersApi.getCharacters()

        val result = response as? BasicResultModel.Success<CharactersRespnse>
        assertNotNull(result)
        assertEquals(result.data.info.count, 1)
        assertEquals(result.data.results[0].name, "Rick Sanchez")
        assertEquals(result.data.results[0].species, "Human")
        assertEquals(result.data.results[0].image, "https://rickandmortyapi.com/api/character/avatar/1.jpeg")
    }

    @Test
    fun testGetCharactersApierror() = runTest {
        getCharactersApi = GetCharactersApi(mockClient(mockEngineError))

        val response = getCharactersApi.getCharacters()

        val result = response as? BasicResultModel.Error<String>
        assertNotNull(result)
        assertContains(result.error, "500 Internal Server Error")
    }
}