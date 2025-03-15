package com.example.weatherman.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    val forecastdayDto: List<ForecastdayDto>
)