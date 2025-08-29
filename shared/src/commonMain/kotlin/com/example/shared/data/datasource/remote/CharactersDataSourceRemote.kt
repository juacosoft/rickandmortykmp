package com.example.shared.data.datasource.remote

import com.example.shared.data.BasicResultModel
import com.example.shared.data.client.GetCharactersApi
import com.example.shared.data.datasource.CharactersDataSource
import com.example.shared.data.model.CharactersRespnse

class CharactersDataSourceRemote(
    private val getCharactersApi: GetCharactersApi
): CharactersDataSource {

    override suspend fun fetchCharacters(): BasicResultModel<CharactersRespnse, String> {
        return getCharactersApi.getCharacters()
    }
}