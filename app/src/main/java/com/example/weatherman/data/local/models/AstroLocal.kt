package com.example.weatherman.data.local.models

data class AstroLocal(
    val isMoonUp: Boolean,
    val isSunUp: Boolean,
    val moonIllumination: Int,
    val moonPhase: String,
    val moonrise: String,
    val moonSet: String,
    val sunrise: String,
    val sunset: String
)