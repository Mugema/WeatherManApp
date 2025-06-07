package com.example.weatherman.presentation.models

data class CurrentWeather(
    val airQualityUi: AirQualityUi,
    val cloud: Int, //cloud cover as a percentage
    val condition: ConditionUI,
    val feelsLike: Double,
    val gust: Double,
    val humidity: Int,
    val isDay: Int,
    val lastUpdated: String,
    val precipitationInInches: Double,
    val pressure: Double,//mb
    val temp: Double,
    val uv: Double,
    val visibility: Double,
    val windDegree: Int,
    val windDirection: String,
    val windSpeed: Double,
    val windChill: Double,
    val location: LocationUI
)