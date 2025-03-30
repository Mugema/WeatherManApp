package com.example.weatherman.data.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ForecastLocal(
    @PrimaryKey val date: String,
    @Embedded val localLocation: LocationLocal,
    @Embedded val astro: AstroLocal,
    @Embedded val day: DayLocal,
)
