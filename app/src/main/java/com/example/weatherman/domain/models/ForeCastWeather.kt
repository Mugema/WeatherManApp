package com.example.weatherman.domain.models

data class ForeCastWeather(
    val date: String,
    val localLocation: Location,
    val astro: Astro,
    val day: Day,
    val hours:List<Hour>
)