package com.example.weatherman.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastdayDto(
    val astro: AstroDto,
    val date: String,
    val date_epoch: Int,
    @SerialName("day") val dayDto: DayDto,
    val hour: List<HourDto>
)