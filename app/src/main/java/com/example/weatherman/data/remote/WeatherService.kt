package com.example.weatherman.data.remote

import com.example.weatherman.domain.DataErrors
import com.example.weatherman.domain.Result
import com.example.weatherman.domain.models.CurrentWeather
import com.example.weatherman.domain.models.ForeCastWeather

interface WeatherService {

    suspend fun getCurrentWeather(
        location:String,
        airQuality: String
    ):Result<CurrentWeather,DataErrors.RemoteError>

    suspend fun getForeCast(
        location: String,
        airQuality: String = "no",
        days: Int = 1
    ):Result<ForeCastWeather,DataErrors.RemoteError>

}