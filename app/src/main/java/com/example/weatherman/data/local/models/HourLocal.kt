package com.example.weatherman.data.local.models

import androidx.room.Embedded
import androidx.room.Entity

@Entity(primaryKeys = ["date","time","lon","lat"])
data class HourLocal(
    val date:String,
    val time: String,
    val chanceOfRain: Int,
    val chanceOfSnow: Int,
    val cloud: Int,
    val lat: Double,
    val lon: Double,
    @Embedded val condition: ConditionLocal,
    val feelsLike: Double,
    val windStrength: Double,
    val humidity: Int,
    val is_day: Int,
    val precipitationInInches: Double,
    val pressureInInches: Double,
    val snowInCm: Double,
    val temperatureInC: Double,
    val uv: Double,
    val visualInKm: Double,
    val willItRain: Int,
    val willItSnow: Int,
    val windDegree: Int,
    val windDirection: String,
    val windInKph: Double,
    val windChill: Double,
)
