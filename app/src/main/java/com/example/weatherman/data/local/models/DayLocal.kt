package com.example.weatherman.data.local.models

import androidx.room.Embedded

data class DayLocal(
    val avgHumidity: Int,
    val avgTemperature: Double,
    val avgVisibility: Double,
    @Embedded val condition: ConditionLocal,
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