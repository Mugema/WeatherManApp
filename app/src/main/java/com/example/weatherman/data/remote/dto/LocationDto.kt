package com.example.weatherman.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: Double,
    val name: String,
    val region: String,
    val tz_id: String
)