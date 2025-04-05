package com.example.weatherman.data.local.models

data class LocationLocal(
    val country: String,
    val lat: Double,
    val localtime: String,
    val lon: Double,
    val name: String,
    val region: String,
    val tz_id: String
)
