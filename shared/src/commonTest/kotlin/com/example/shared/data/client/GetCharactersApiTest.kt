package com.example.shared.data.client

import com.example.shared.data.BasicResultModel
import com.example.shared.data.model.CharactersRespnse
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class GetCharactersApiTest {

    private val getCharactersApi = GetCharactersApi()

    val expectedJson = "{" +
            "\"info\": {\n" +
            "    \"count\": 1,\n" +
            "    \"pages\": 1,\n" +
            "    \"next\": \"https://rickandmortyapi.com/api/character/?page=2\",\n" +
            "    \"prev\": null\n" +
            "  }," +
            "\"results\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"Rick Sanchez\",\n" +
            "      \"status\": \"Alive\",\n" +
            "      \"species\": \"Human\",\n" +
            "      \"type\": \"\",\n" +
            "      \"gender\": \"Male\",\n" +
            "      \"origin\": {\n" +
            "        \"name\": \"Earth\",\n" +
            "        \"url\": \"https://rickandmortyapi.com/api/location/1\"\n" +
            "      },\n" +
            "      \"location\": {\n" +
            "        \"name\": \"Earth\",\n" +
            "        \"url\": \"https://rickandmortyapi.com/api/location/20\"\n" +
            "      },\n" +
            "      \"image\": \"https://rickandmortyapi.com/api/character/avatar/1.jpeg\",\n" +
            "      \"episode\": [\n" +
            "        \"https://rickandmortyapi.com/api/episode/1\",\n" +
            "        \"https://rickandmortyapi.com/api/episode/2\",\n" +
            "        // ...\n" +
            "      ],\n" +
            "      \"url\": \"https://rickandmortyapi.com/api/character/1\",\n" +
            "      \"created\": \"2017-11-04T18:48:46.250Z\"\n" +
            "    },\n" +
            "  ]" +
            "}"

    private fun mockClient(mockEngine: MockEngine)= HttpClient(mockEngine) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                allowTrailingComma = true
            })
        }
    }

    @Test
    fun testGetCharractersApiSuccess() = runTest {
        val mockEngine = MockEngine { request ->
            respond(
                content = ByteReadChannel(expectedJson),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        getCharactersApi.client = mockClient(mockEngine)
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
        val mockEngine = MockEngine { request ->
            respond(
                content = ByteReadChannel("{}"),
                status = HttpStatusCode.Forbidden,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        getCharactersApi.client = mockClient(mockEngine)
        val response = getCharactersApi.getCharacters()

        val result = response as? BasicResultModel.Error<String>
        assertNotNull(result)
        assertEquals(result.error, "ServerError")
    }
}