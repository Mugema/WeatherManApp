package com.example.weatherman.navigation

import kotlinx.serialization.Serializable

sealed interface Routes{
    @Serializable
    data object Home:Routes

    @Serializable
    data object Tomorrow:Routes

    @Serializable
    data object Forecast:Routes
}