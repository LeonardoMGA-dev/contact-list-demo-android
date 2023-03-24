package com.example.countify.util

sealed interface Either<T> {
    data class Success<T>(val value: T) : Either<T>
    data class Error<T>(val error: ErrorEntity) : Either<T>
}