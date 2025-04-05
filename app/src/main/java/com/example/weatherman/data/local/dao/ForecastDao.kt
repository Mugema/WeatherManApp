package com.example.weatherman.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherman.data.local.models.ForecastLocal
import com.example.weatherman.data.local.models.HourLocal

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addForecast(forecast:ForecastLocal)

    @Query("SELECT * FROM FORECASTLOCAL JOIN HourLocal ON HourLocal.date WHERE name =:city")
    suspend fun getForeCast(city:String):Map<ForecastLocal,List<HourLocal>>
}