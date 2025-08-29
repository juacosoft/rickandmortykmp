package com.example.shared.data.datasource.remote

import com.example.shared.data.BasicResultModel
import com.example.shared.data.client.GetCharactersApi
import com.example.shared.data.commons.mockClient
import com.example.shared.data.commons.mockEngineError
import com.example.shared.data.commons.mockEngineSuccess
import com.example.shared.data.datasource.CharactersDataSource
import io.ktor.client.HttpClient
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class CharactersDataSourceRemoteTest: KoinTest {

    private val dataSource: CharactersDataSource by inject()

    private lateinit var client: HttpClient

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                module {
                    single{ GetCharactersApi (client)}
                    single<CharactersDataSource> { CharactersDataSourceRemote(get()) }
                }
            )
        }
    }

    @Test
    fun testFetchCharactersSuccess() = runTest {
        client = mockClient(mockEngineSuccess)

        val result = dataSource.fetchCharacters()
        assertTrue(result is BasicResultModel.Success)
    }

    @Test
    fun testFetchCharactersError() = runTest {
        client = mockClient(mockEngineError)
        val result = dataSource.fetchCharacters()
        assertTrue(result is BasicResultModel.Error)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}