package com.example.weatherman.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class CurrentWeatherDto(
    val current: CurrentDto,
    val location: LocationDto
)