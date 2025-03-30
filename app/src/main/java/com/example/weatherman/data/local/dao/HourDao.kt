package com.example.weatherman.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherman.data.local.models.HourLocal

@Dao
interface HourDao {
    @Insert
    suspend fun addHours(hourLocal: List<HourLocal>)

    @Query("SELECT * FROM hourlocal WHERE date = :date")
    suspend fun getHours(date:String):List<HourLocal>

}