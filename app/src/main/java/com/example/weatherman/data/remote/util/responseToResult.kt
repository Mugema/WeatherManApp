package com.example.weatherman.data.remote.util

import com.example.weatherman.domain.DataErrors
import com.example.weatherman.domain.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend  inline fun <reified D>responseToResult(response:HttpResponse):Result<D,DataErrors.RemoteError>{
    return when(response.status.value){
        in 200..299 ->{
            try {
                Result.Success(response.body())
            } catch (e: NoTransformationFoundException){
                Result.Error(DataErrors.RemoteError.SERIALIZATION)
            }
        }
        403 -> Result.Error(DataErrors.RemoteError.TOO_MANY_REQUESTS)
        else -> Result.Error(DataErrors.RemoteError.UNKNOWN)
    }
}

