package com.example.shared.di

fun appModule() = listOf(
    platformModule,
    networkModule,
    dataSourceModule,
    repositoryModule,
    charactersModule
)