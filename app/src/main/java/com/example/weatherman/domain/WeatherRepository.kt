package com.example.weatherman.domain

import com.example.weatherman.data.remote.dto.CurrentWeatherDto
import com.example.weatherman.data.remote.dto.ForeCastWeatherDto
import com.example.weatherman.domain.models.Astro
import com.example.weatherman.domain.models.CurrentWeather
import com.example.weatherman.domain.models.Day
import com.example.weatherman.domain.models.ForeCastWeather
import com.example.weatherman.domain.models.Hour
import kotlinx.coroutines.flow.Flow

interface WeatherRepository  {
    suspend fun getCurrentWeather(location:String):Flow<Result<CurrentWeather,DataErrors.RemoteError>>

    suspend fun getForeCastWeather(location: String):Flow<Result<ForeCastWeather,DataErrors.RemoteError>>

    suspend fun dataSynchronization(
        currentWeatherDto: CurrentWeatherDto? = null,
        foreCastWeatherDto: ForeCastWeatherDto? = null
    )

    suspend fun dataUpdate(location: String)

    suspend fun addNewLocation(location:String)

    suspend fun getCurrentLocation():String?

    suspend fun getHourData(date:String):List<Hour>

    suspend fun getAstroData(date: String):Astro?

    suspend fun getDayDetails(date: String):Day?

}