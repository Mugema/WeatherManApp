package com.example.weatherman.domain.models


data class CurrentWeather(
    val airQuality: AirQuality,
    val cloud: Int, //cloud cover as a percentage
    val condition: Condition,
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
    val location: Location
)