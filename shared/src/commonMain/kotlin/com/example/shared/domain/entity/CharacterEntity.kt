package com.example.shared.domain.entity

import com.example.shared.data.model.CharacterModel
import com.example.shared.data.model.CharactersRespnse

data class CharactersEntity(
    val characters: List<CharacterEntity>,
    val next: String?,
    val prev: String?,
    val count: Int
)

data class CharacterEntity(
    val id: Int,
    val name: String,
    val species: SPECIES,
    val gender: Gender,
    val image: String,
)

enum class Gender {
    MALE,
    FEMALE,
    UNKNOWN
}

enum class SPECIES {
    HUMAN,
    ALIEN
}

fun CharactersRespnse.toDomain() = CharactersEntity(
    characters = results.map { it.toDomain() },
    next = info.next,
    prev = info.prev,
    count = info.count
)

fun CharacterModel.toDomain() = CharacterEntity(
    id = id,
    name = name,
    species = SPECIES.valueOf(species.uppercase()),
    gender = Gender.valueOf(gender.uppercase()),
    image = image
)

sealed class CharactersResult {
    object Loading : CharactersResult()
    data class Success(val characterResponse: CharactersEntity) : CharactersResult()
    data class Error(val error: String) : CharactersResult()
}
