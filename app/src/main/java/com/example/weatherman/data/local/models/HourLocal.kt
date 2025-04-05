package com.example.weatherman.data.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HourLocal(
    @PrimaryKey val date:String,
    val chanceOfRain: Int,
    val chanceOfSnow: Int,
    val cloud: Int,
    @Embedded val condition: ConditionLocal,
    val feelsLike: Double,
    val windStrength: Double,
    val humidity: Int,
    val is_day: Int,
    val precipitationInInches: Double,
    val pressureInInches: Double,
    val snowInCm: Double,
    val temperatureInC: Double,
    val time: String,
    val uv: Double,
    val visualInKm: Double,
    val willItRain: Int,
    val willItSnow: Int,
    val windDegree: Int,
    val windDirection: String,
    val windInKph: Double,
    val windChill: Double,
)
