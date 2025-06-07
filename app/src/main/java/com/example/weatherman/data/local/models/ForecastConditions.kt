package com.example.weatherman.data.local.models

import androidx.room.ColumnInfo
import com.example.weatherman.domain.models.Condition

data class ForeCastConditions(
    @ColumnInfo(name = "text") val condition:String?,
    @ColumnInfo(name = "code") val code:Int?,
    @ColumnInfo(name = "date") val date: String?
)

fun ForeCastConditions.toConditions(): Condition{
    return Condition(
        date = this.date ?: "",
        icon = this.code ?: 0 ,
        text = this.condition ?: ""
    )
}