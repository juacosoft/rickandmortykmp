package com.example.shared.domain.usecase

import com.example.shared.domain.entity.CharactersResult
import com.example.shared.domain.repository.CharactersRepository

class GetCharactersUseCase(
    private val repository: CharactersRepository
) {

    suspend operator fun invoke(): CharactersResult =
        repository.fetchCharacters()
}