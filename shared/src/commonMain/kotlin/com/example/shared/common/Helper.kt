package com.example.shared.common

import com.example.shared.di.appModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}