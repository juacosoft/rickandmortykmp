package com.example.shared.di

import com.example.shared.data.datasource.CharactersDataSource
import com.example.shared.data.datasource.remote.CharactersDataSourceRemote
import org.koin.dsl.module

val dataSourceModule = module {
    single<CharactersDataSource> { CharactersDataSourceRemote(get()) }
}