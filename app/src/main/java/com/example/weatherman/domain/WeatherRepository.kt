package com.example.weatherman.domain

import com.example.weatherman.domain.models.CurrentWeather
import com.example.weatherman.domain.models.ForeCastWeather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository  {
    suspend fun getCurrentWeather(location:String):Flow<Result<CurrentWeather,DataErrors.RemoteError>>

    suspend fun getForeCastWeather(location: String):Flow<Result<ForeCastWeather,DataErrors.RemoteError>>
}