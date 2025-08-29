package com.example.shared.data.remoterepository

import com.example.shared.data.BasicResultModel
import com.example.shared.data.datasource.CharactersDataSource
import com.example.shared.domain.entity.CharactersResult
import com.example.shared.domain.entity.toDomain
import com.example.shared.domain.repository.CharactersRepository

class DefaultCharactersRepository(
    private val dataSource: CharactersDataSource
): CharactersRepository {

    override suspend fun fetchCharacters(): CharactersResult {
        val result = dataSource.fetchCharacters()
        return when (result) {
            is BasicResultModel.Error ->
                CharactersResult.Error(result.error)
            is BasicResultModel.Success ->
                CharactersResult.Success(result.data.toDomain())
        }
    }

}