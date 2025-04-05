package com.example.weatherman.presentation.components

sealed interface OnAction{
    data class OnSearch(val location:String): OnAction
    data class MakeSearch(val location: String): OnAction
}