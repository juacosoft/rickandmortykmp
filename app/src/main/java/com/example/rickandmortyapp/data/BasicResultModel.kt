package com.example.rickandmortyapp.data

sealed class BasicResultModel<out T, out E> {
    data class Success<out T>(val data: T) : BasicResultModel<T, Nothing>()
    data class Error<out E>(val error: E) : BasicResultModel<Nothing, E>()
}

