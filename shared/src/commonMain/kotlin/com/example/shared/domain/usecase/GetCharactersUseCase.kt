package com.example.shared.domain.usecase

import com.example.shared.data.BasicResultModel
import com.example.shared.data.client.GetCharactersApi
import com.example.shared.domain.entity.CharactersResult
import com.example.shared.domain.entity.toDomain

class GetCharactersUseCase {

    companion object {
        private val instance = GetCharactersUseCase()

        fun getInstance() = instance
    }

    private val api = GetCharactersApi()

    suspend operator fun invoke(): CharactersResult {
        return when (val result = api.getCharacters()) {
            is BasicResultModel.Success -> CharactersResult.Success(result.data.toDomain())
            is BasicResultModel.Error -> CharactersResult.Error(result.error)
        }
    }

}