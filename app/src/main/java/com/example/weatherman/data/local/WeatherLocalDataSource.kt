package com.example.weatherman.data.local

import android.util.Log
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
   private val ioDispatcher: CoroutineDispatcher,
   private val dataStore:PrefUtil

) {
   suspend fun updateCurrentWeather(currentWeather:CurrentWeatherLocal){
      withContext(ioDispatcher){
         currentWeatherDao.updateCurrentWeather(currentWeather)
      }
   }
   suspend fun getCurrentWeather(location: String):CurrentWeather?{
      val dbReturn:CurrentWeather
      try {
          dbReturn= currentWeatherDao.getCurrentWeather(location).toCurrentWeather()
      } catch (error:NullPointerException){
         return null
      }
      return dbReturn
   }

   suspend fun addForecast(forecastLocal: ForecastLocal){
      withContext(ioDispatcher){
         foreCastDao.addForecast(forecastLocal)
      }
   }

   suspend fun addForecastHours(hours:List<HourLocal>){
      withContext(ioDispatcher){
         hourDao.addHours(hours)
      }
   }

   suspend fun getHours(date:String):List<Hour>{
      return hourDao.getHours(date).map { hour-> hour.toHour()}
   }

   suspend fun getWeatherForecast(location:String): ForeCastWeather?{
      Log.d("Local data source",location)
      val forecast:List<ForeCastWeather>
      try {
         forecast= foreCastDao.getForeCast(location).map { entry ->
            entry.key.toForecastWeather(entry.value.map { it.toHour() })
         }
      } catch (error:NullPointerException){ return null }

      return try {
         forecast.first()
      }catch (error:NoSuchElementException){ null }
   }

   suspend fun getCurrentLocation(key:String="currentLocation"):String? {
      return dataStore.getLocation(key)

   }
   suspend fun addLocation(key:String="currentLocation",location:String){
      dataStore.addLocation(key,location)

   }
}