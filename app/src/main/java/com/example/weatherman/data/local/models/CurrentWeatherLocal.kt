package com.example.weatherman.data.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrentWeatherLocal(
    @PrimaryKey val lastUpdatedEpoch: Int,
    @Embedded val airQuality: AirQualityLocal,
    val cloud: Int, //cloud cover as a percentage
    @Embedded val condition: ConditionLocal,
    @Embedded val locationLocal: LocationLocal,
    val dewPoint: Double,
    val humidity: Int,
    val isDay: Int,
    val lastUpdated: String,
    val precipitationInInches: Double,
    val pressure: Double,//mb

    val feelsLike: Double,
    val temp: Double,
    val uv: Double,

    val visibility: Double,

    val gust: Double,
    val windDegree: Int,
    val windDirection: String,
    val windSpeed: Double,
    val windChill: Double,
)
