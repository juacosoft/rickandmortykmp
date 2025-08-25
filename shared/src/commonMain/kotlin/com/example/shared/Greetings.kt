package com.example.shared

class Greetings {
    private val platform = getPlatform()

    fun greet(): String {
        return "This device is a ${platform.name}!"
    }
}