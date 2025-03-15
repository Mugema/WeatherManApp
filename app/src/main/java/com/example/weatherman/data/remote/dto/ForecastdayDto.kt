package com.example.weatherman.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForecastdayDto(
    val astroDto: AstroDto,
    val date: String,
    val date_epoch: Int,
    val dayDto: DayDto,
    val hourDto: List<HourDto>
)