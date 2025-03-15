package com.example.weatherman.presentation.models

import androidx.compose.ui.graphics.Color

data class AirToColor(
    val ozone:Pair<String,Color> = "Ozone" to Color.Blue,
    val carbon:Pair<String,Color> = "Carbon Dioxide" to Color.Green,
    val nitro:Pair<String,Color> = "Nitrogen" to Color.Red,
    val sulphur:Pair<String,Color> = "Sulphur Dioxide" to Color.Yellow
)