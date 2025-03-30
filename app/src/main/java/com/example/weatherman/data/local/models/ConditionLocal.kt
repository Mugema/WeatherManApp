package com.example.weatherman.data.local.models

import androidx.room.Entity

data  class ConditionLocal(
    val code: Int,
    val icon: String,
    val text: String
)