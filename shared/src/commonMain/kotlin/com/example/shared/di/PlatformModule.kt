package com.example.shared.di

import com.example.shared.Platform
import com.example.shared.getPlatform
import org.koin.dsl.module

val platformModule = module {
    single<Platform> { getPlatform() }
}