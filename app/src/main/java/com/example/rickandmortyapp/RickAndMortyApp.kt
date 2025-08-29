package com.example.rickandmortyapp

import android.app.Application
import com.example.shared.di.appModule
import org.koin.core.context.startKoin

class RickAndMortyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule())
        }
    }
}