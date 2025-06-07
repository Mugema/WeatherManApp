package com.example.weatherman.data.local.models

data class LocationLocal(
    val country: String,
    val lat: Double,
    val lon: Double,
    val localtime: String,
    val name: String,
    val region: String,
    val tz_id: String
)
