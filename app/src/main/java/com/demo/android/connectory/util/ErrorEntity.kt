package com.example.countify.util

sealed interface ErrorEntity {
    data class HttpError(val httpCode: Int) : ErrorEntity
    object EmptyBody : ErrorEntity
    data class UnknownError(val exception: Exception) : ErrorEntity
}