package com.example.weatherman.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvidesModule {

    @Provides
    @Singleton
    fun ProvidesKtorClient():HttpClient{
        val client= HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint=true
                        isLenient=true
//                        ignoreUnknownKeys=true
                    }
                )
            }
            install(Logging){
                level=LogLevel.ALL
            }
        }
    return client
    }

    @Provides
    @Singleton
    fun ProvidesCoroutine():CoroutineDispatcher{
        return Dispatchers.IO
    }
}