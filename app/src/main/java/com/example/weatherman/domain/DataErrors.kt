package com.example.weatherman.domain

sealed interface DataErrors:Error {
    enum class RemoteError:DataErrors{
        WRONG_ARGUMENTS,
        ACCESS_DENIED,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERIALIZATION,
        UNKNOWN
    }
    enum class LocalError:DataErrors{}
}