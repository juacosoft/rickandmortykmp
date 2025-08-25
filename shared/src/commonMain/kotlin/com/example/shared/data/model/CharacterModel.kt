package com.example.shared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val image: String,
)
