package com.example.weatherman.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherman.data.local.dao.CurrentWeatherDao
import com.example.weatherman.data.local.dao.ForecastDao
import com.example.weatherman.data.local.dao.HourDao
import com.example.weatherman.data.local.models.CurrentWeatherLocal
import com.example.weatherman.data.local.models.ForecastLocal
import com.example.weatherman.data.local.models.HourLocal

@Database(
    entities = [CurrentWeatherLocal::class, HourLocal::class, ForecastLocal::class],
    version = 3,
)
abstract class WeatherDb(): RoomDatabase() {

    abstract fun getCurrentWeatherDao(): CurrentWeatherDao
    abstract fun getHourDao(): HourDao
    abstract fun getForecastDao(): ForecastDao

}