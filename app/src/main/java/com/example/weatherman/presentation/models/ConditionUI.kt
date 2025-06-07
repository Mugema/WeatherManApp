package com.example.weatherman.presentation.models

import com.example.weatherman.domain.models.Condition

data class ConditionUI(
    val date: String,
    val icon: Int,
    val text: String
)

fun Condition.toConditionUI(date: String,icon: Int): ConditionUI{
    return ConditionUI(
        date = date,
        icon = icon,
        text = this.text,
    )
}

