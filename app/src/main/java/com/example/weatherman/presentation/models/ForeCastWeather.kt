package com.example.weatherman.presentation.models


data class ForeCastWeather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)