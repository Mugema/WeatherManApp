package com.example.weatherman.data.local.models

import androidx.room.Embedded
import androidx.room.Entity

@Entity(primaryKeys = ["date","lon","lat"])
data class ForecastLocal(
    val date: String,
    @Embedded val localLocation: LocationLocal,
    @Embedded val astro: AstroLocal,
    @Embedded val day: DayLocal,
)
