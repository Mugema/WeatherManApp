package com.example.weatherman.di

import android.content.Context
import androidx.room.Room
import com.example.weatherman.data.local.PrefUtil
import com.example.weatherman.data.local.WeatherDb
import com.example.weatherman.data.local.dao.CurrentWeatherDao
import com.example.weatherman.data.local.dao.ForecastDao
import com.example.weatherman.data.local.dao.HourDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesKtorClient():HttpClient{
        val client= HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint=true
                        isLenient=true
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
    fun providesCoroutine():CoroutineDispatcher{
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun providesWeatherDatabase(@ApplicationContext context: Context): WeatherDb{
        return Room
            .databaseBuilder(context, WeatherDb::class.java,"weatherDb")
            .build()
    }

    @Provides
    @Singleton
    fun providesCurrentWeatherDao(database: WeatherDb): CurrentWeatherDao{
        return  database.getCurrentWeatherDao()
    }

    @Provides
    @Singleton
    fun providesForecastDao(database: WeatherDb):ForecastDao{
        return database.getForecastDao()
    }

    @Provides
    @Singleton
    fun providesHourDao(database: WeatherDb):HourDao{
        return database.getHourDao()
    }

    @Provides
    @Singleton
    fun providesDataStore(@ApplicationContext context:Context):PrefUtil{
        return PrefUtil(context)
    }
}