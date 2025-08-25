package com.example.shared.data.client

import com.example.shared.data.BasicResultModel
import com.example.shared.data.model.CharactersRespnse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class GetCharactersApi {

    private val client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getCharacters(): BasicResultModel<CharactersRespnse, String> {
        return try {
            val response = client.get("https://rickandmortyapi.com/api/character").body<CharactersRespnse>()
            BasicResultModel.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            BasicResultModel.Error(e.message ?: "Unknown error")
        }
    }
}
