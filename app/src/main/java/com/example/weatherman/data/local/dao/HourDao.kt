package com.example.weatherman.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherman.data.local.models.HourLocal

@Dao
interface HourDao {
    @Insert
    suspend fun addHours(hourLocal: List<HourLocal>)

    @Query("SELECT * FROM hourlocal WHERE date = :date AND lon = :lon AND lat = :lat  ")
    suspend fun getHours(date:String,lon: Double,lat: Double):List<HourLocal>

}