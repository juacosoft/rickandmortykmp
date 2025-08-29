package com.example.shared.domain.repository

import com.example.shared.domain.entity.CharactersResult

interface CharactersRepository {

    suspend fun fetchCharacters(): CharactersResult
}