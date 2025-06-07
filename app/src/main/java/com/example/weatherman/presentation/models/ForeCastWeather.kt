package com.example.weatherman.presentation.models


data class ForeCastWeather(
    val date: String,
    val localLocation: LocationUI,
    val astro: Astro,
    val day: Day,
    val hours:List<Hour>
)