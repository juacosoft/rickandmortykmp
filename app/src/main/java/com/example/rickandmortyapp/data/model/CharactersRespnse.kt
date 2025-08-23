package com.example.rickandmortyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharactersRespnse(
    val info: Info,
    val results: List<CharacterModel>
)

@Serializable
data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String?
)
