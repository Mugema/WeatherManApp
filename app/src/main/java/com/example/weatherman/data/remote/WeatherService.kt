package com.example.weatherman.data.remote

import com.example.weatherman.data.remote.dto.CurrentWeatherDto
import com.example.weatherman.data.remote.dto.ForeCastWeatherDto
import com.example.weatherman.domain.DataErrors
import com.example.weatherman.domain.Result

interface WeatherService {

    suspend fun getCurrentWeather(
        location:String,
        airQuality: String
    ):Result<CurrentWeatherDto,DataErrors.RemoteError>

    suspend fun getForeCast(
        location: String,
        airQuality: String = "no",
        days: Int = 1
    ):Result<ForeCastWeatherDto,DataErrors.RemoteError>

}