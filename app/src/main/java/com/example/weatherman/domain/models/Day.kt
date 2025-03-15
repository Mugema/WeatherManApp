package com.example.weatherman.domain.models

data class Day(
    val avgHumidity: Int,
    val avgTemperature: Double,
    val avgVisibility: Double,
    val condition: Condition,
    val chanceOfRain: Int,
    val chanceOfSnow: Int,
    val willItRain: Boolean,
    val willItSnow: Boolean,
    val maxTemperature: Double,
    val maxWind: Double,
    val minTemperature: Double,
    val totalPrecipitation: Double,
    val totalSnow: Double,
    val uv: Double
)