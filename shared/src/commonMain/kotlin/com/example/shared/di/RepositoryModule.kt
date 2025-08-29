package com.example.shared.di

import com.example.shared.data.remoterepository.DefaultCharactersRepository
import com.example.shared.domain.repository.CharactersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CharactersRepository> { DefaultCharactersRepository(get()) }
}