package com.example.shared.di

import com.example.shared.domain.usecase.GetCharactersUseCase
import com.example.shared.presentation.CharactersViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val charactersModule = module {
    factory { GetCharactersUseCase(get()) }
    viewModelOf(::CharactersViewModel)
}