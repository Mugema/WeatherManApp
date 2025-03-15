package com.example.weatherman.domain.models

data class Astro(
    val isMoonUp: Boolean,
    val isSunUp: Boolean,
    val moonIllumination: Int,
    val moonPhase: String,
    val moonrise: String,
    val moonSet: String,
    val sunrise: String,
    val sunset: String
)