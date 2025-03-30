package com.example.weatherman.data.local.models

import androidx.room.Entity

data class AirQualityLocal(
    val carbonMonoxide: Double,
    val nitrogenDioxide: Double,
    val ozone: Double,
    val sulphurDioxide: Double,
)
