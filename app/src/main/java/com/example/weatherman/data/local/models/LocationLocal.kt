package com.example.weatherman.data.local.models

import androidx.room.Entity

data class LocationLocal(
    val country: String,
    val lat: Double,
    val localtime: String,
    val lon: Double,
    val name: String,
    val region: String,
    val tz_id: String
)
