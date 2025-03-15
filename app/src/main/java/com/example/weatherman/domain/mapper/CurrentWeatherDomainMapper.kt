package com.example.weatherman.domain.mapper

import com.example.weatherman.domain.models.AirQuality
import com.example.weatherman.presentation.models.AirQualityUi

fun AirQuality.toAirQualityUi():AirQualityUi{
    val total = carbonMonoxide+nitrogenDioxide+ozone+sulphurDioxide
    return AirQualityUi(
        carbonMonoxide = (carbonMonoxide/total).toFloat(),
        nitrogenDioxide = (nitrogenDioxide/total).toFloat(),
        ozone = (ozone/total).toFloat(),
        sulphurDioxide = (sulphurDioxide/total).toFloat()
    )
}