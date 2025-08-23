package com.example.rickandmortyapp.data.client

import android.util.Log
import com.example.rickandmortyapp.data.BasicResultModel
import com.example.rickandmortyapp.data.model.CharactersRespnse
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
            Log.d("TAG-GET-CHARACTERS", "getCharacters: $response")
            BasicResultModel.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            BasicResultModel.Error(e.message ?: "Unknown error")
        }
    }
}
