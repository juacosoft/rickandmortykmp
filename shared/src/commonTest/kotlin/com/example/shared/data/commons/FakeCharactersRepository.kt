package com.example.shared.data.commons

import com.example.shared.domain.entity.CharactersEntity
import com.example.shared.domain.entity.CharactersResult
import com.example.shared.domain.repository.CharactersRepository

class FakeCharactersRepository: CharactersRepository {

    private var shouldReturnError = false

    private var characters: CharactersEntity? = null

    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun fetchCharacters(): CharactersResult {
        if (shouldReturnError) {
            return CharactersResult.Error("Test error")
        } else {
            return CharactersResult.Success(characters?: getCharacters())
        }
    }

    fun setCharacters(characters: CharactersEntity) {
        this.characters = characters
    }

    private fun getCharacters(): CharactersEntity = CharactersEntity(
        characters = emptyList(),
        next = "",
        prev = "",
        count = 0
    )
}