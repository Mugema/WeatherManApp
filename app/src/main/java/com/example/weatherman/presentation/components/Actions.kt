package com.example.weatherman.presentation.components

sealed interface OnAction{
    data class OnSearch(val location:String): OnAction
    data class MakeSearch(val location: String): OnAction
}

sealed interface ForeScreenAction{
    data class OnDayClick(val day:String):ForeScreenAction
}