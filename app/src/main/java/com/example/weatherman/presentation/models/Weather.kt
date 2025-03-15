package com.example.weatherman.presentation.models

import com.example.weatherman.R

interface Weather {
    val icon:Int
    val attrValue: String
    val change:Int

}

data class Wind(
    override val icon:Int = R.drawable.wind,
    override val attrValue: String,
    override val change:Int=0
):Weather

data class Temperature(
    override val icon:Int = R.drawable.thermometer,
    override val attrValue:String,
    override val change:Int=0
):Weather

data class Uv(
    override val icon:Int = R.drawable.rays,
    override val attrValue:String,
    override val change:Int=0
):Weather

data class Pressure(
    override val icon:Int = R.drawable.barometer,
    override val attrValue:String,
    override val change:Int=0
):Weather


