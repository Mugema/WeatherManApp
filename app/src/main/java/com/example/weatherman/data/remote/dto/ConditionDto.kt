package com.example.weatherman.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ConditionDto(
    val code: Int,
    val icon: String,
    val text: String
)