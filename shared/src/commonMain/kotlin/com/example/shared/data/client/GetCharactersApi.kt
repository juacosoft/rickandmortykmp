package com.example.shared.data.client

import com.example.shared.common.HOST_URL
import com.example.shared.common.PATH_CHARACTERS
import com.example.shared.data.BasicResultModel
import com.example.shared.data.model.CharactersRespnse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get

class GetCharactersApi(private val client: HttpClient) {

    suspend fun getCharacters(): BasicResultModel<CharactersRespnse, String> {
        return try {
            val response = client.get(HOST_URL + PATH_CHARACTERS).body<CharactersRespnse>()
            BasicResultModel.Success(response)
        } catch (e: ClientRequestException) {
            e.printStackTrace()
            BasicResultModel.Error("ServerError")
        } catch (e: Exception) {
            e.printStackTrace()
            BasicResultModel.Error(e.message ?: "Unknown error")
        }
    }
}
