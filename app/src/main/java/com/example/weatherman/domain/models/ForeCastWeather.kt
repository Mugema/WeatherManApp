package com.example.weatherman.domain.models


data class ForeCastWeather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)