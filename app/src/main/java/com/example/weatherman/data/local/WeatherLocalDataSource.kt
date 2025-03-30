package com.example.weatherman.data.local

import com.example.weatherman.data.local.dao.CurrentWeatherDao
import com.example.weatherman.data.local.dao.ForecastDao
import com.example.weatherman.data.local.dao.HourDao
import com.example.weatherman.data.local.models.CurrentWeatherLocal
import com.example.weatherman.data.local.models.ForecastLocal
import com.example.weatherman.data.local.models.HourLocal
import com.example.weatherman.data.mapper.toCurrentWeather
import com.example.weatherman.data.mapper.toForecastWeather
import com.example.weatherman.data.mapper.toHour
import com.example.weatherman.domain.models.CurrentWeather
import com.example.weatherman.domain.models.ForeCastWeather
import com.example.weatherman.domain.models.Hour
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class WeatherLocalDataSource @Inject constructor(
   private val currentWeatherDao: CurrentWeatherDao,
   private val foreCastDao: ForecastDao,
   private val hourDao: HourDao,
   private val ioDispatcher: CoroutineDispatcher

) {
   suspend fun updateCurrentWeather(currentWeather:CurrentWeatherLocal){
      withContext(ioDispatcher){
         currentWeatherDao.updateCurrentWeather(currentWeather)
      }
   }
   suspend fun getCurrentWeather():CurrentWeather{
      return currentWeatherDao.getCurrentWeather().toCurrentWeather()
   }

   suspend fun addForecast(forecastLocal: ForecastLocal){
      foreCastDao.addForecast(forecastLocal)
   }

   suspend fun addForecastHours(hours:List<HourLocal>){
      hourDao.addHours(hours)
   }

   private suspend fun getHours(date:String):List<Hour>{
      return hourDao.getHours(date).map { hour-> hour.toHour()}
   }

   suspend fun getWeatherForecast(): ForeCastWeather{
      val forecast = foreCastDao.getForeCast().map { entry ->
        entry.key.toForecastWeather(entry.value.map { it.toHour() })
      }
      return forecast.first()
   }
}