package com.example.weatherman.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForeCastWeatherDto(
    val current: CurrentDto,
    val forecastDto: ForecastDto,
    val locationDto: LocationDto
)