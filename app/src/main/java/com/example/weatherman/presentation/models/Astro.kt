package com.example.weatherman.presentation.models

data class Astro(
    val isMoonUp: Boolean,
    val isSunUp: Boolean,
)

data class AstroState(
    val time:String,
    val event:String,
    val image:Int
)


