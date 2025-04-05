package com.example.weatherman.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherman.data.local.models.CurrentWeatherLocal

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateCurrentWeather(currentWeather:CurrentWeatherLocal)

    @Query("SELECT * FROM CurrentWeatherLocal WHERE name =:location")
    suspend fun getCurrentWeather(location:String):CurrentWeatherLocal


}