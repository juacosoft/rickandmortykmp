package com.example.shared.data.datasource

import com.example.shared.data.BasicResultModel
import com.example.shared.data.model.CharactersRespnse

interface CharactersDataSource {

    suspend fun fetchCharacters(): BasicResultModel<CharactersRespnse, String>
}