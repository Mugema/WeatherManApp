package com.example.weatherman.data.remote.util

import com.example.weatherman.domain.DataErrors
import com.example.weatherman.domain.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun< reified D> safeCall(execute:()->HttpResponse):Result<D,DataErrors.RemoteError>
{
    val response=try {
        execute()
    } catch (e:UnresolvedAddressException){
        return Result.Error(DataErrors.RemoteError.NO_INTERNET)
    } catch (e: SerializationException){
        return Result.Error(DataErrors.RemoteError.SERIALIZATION)
    } catch (e: UnknownError){
        coroutineContext.ensureActive()
        return Result.Error(DataErrors.RemoteError.UNKNOWN)
    }
    return responseToResult(response)

}