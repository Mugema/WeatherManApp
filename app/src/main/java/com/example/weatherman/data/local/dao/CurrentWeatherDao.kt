package com.example.weatherman.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherman.data.local.models.CurrentWeatherLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateCurrentWeather(currentWeather:CurrentWeatherLocal)

    @Query("SELECT * FROM CurrentWeatherLocal")
    suspend fun getCurrentWeather():CurrentWeatherLocal


}