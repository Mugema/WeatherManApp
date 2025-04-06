package com.example.weatherman.data.repository

import android.util.Log.d
import com.example.weatherman.data.local.WeatherLocalDataSource
import com.example.weatherman.data.mapper.toCurrentWeatherLocal
import com.example.weatherman.data.mapper.toForecastLocal
import com.example.weatherman.data.remote.WeatherOnlineDataSource
import com.example.weatherman.data.remote.dto.CurrentWeatherDto
import com.example.weatherman.data.remote.dto.ForeCastWeatherDto
import com.example.weatherman.domain.DataErrors
import com.example.weatherman.domain.Result
import com.example.weatherman.domain.WeatherRepository
import com.example.weatherman.domain.models.Astro
import com.example.weatherman.domain.models.CurrentWeather
import com.example.weatherman.domain.models.Day
import com.example.weatherman.domain.models.ForeCastWeather
import com.example.weatherman.domain.models.Hour
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImplementation @Inject constructor(
    private val weatherRemoteDataSource: WeatherOnlineDataSource,
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher
):WeatherRepository {

    override suspend fun getCurrentWeather(location:String): Flow<Result<CurrentWeather, DataErrors.RemoteError>> {
        val local = weatherLocalDataSource.getCurrentWeather(location)
        if (local == null) {
            val response =
                weatherRemoteDataSource.getCurrentWeather(location = location, airQuality = "yes")
            when (response) {
                is Result.Error -> {
                    return flow { emit(response) }.flowOn(ioDispatcher)
                }

                is Result.Success -> {
                    dataSynchronization(currentWeatherDto = response.data)
                    val remote = weatherLocalDataSource.getCurrentWeather(location)
                    return flow{ emit(Result.Success(remote!!)) }.flowOn(ioDispatcher)
                }
            }
        }
        else{
            return flow { emit(Result.Success(local)) }.flowOn(ioDispatcher)
        }
    }

    override suspend fun getForeCastWeather(location: String): Flow<Result<ForeCastWeather, DataErrors.RemoteError>> {
        val localData = weatherLocalDataSource.getWeatherForecast(location)
        d("getForeCast-$location",localData.toString())
        if (localData==null){
            when(val response = weatherRemoteDataSource.getForeCast(location)){
                is Result.Error -> {
                    return flow{ emit(response) }.flowOn(ioDispatcher)
                }
                is Result.Success ->{
                    dataSynchronization(foreCastWeatherDto = response.data)

                    val remote = weatherLocalDataSource.getWeatherForecast(location)
                    return flow { emit(Result.Success(remote!!)) }.flowOn(ioDispatcher)

                }
            }
        } else return flow { emit(Result.Success(localData)) }.flowOn(ioDispatcher)

    }

    override suspend fun addNewLocation(location: String) {
        weatherLocalDataSource.addLocation(location=location)
    }

    override suspend fun getCurrentLocation(): String? {
        return weatherLocalDataSource.getCurrentLocation()
    }

    override suspend fun dataSynchronization(
        currentWeatherDto: CurrentWeatherDto?,
        foreCastWeatherDto: ForeCastWeatherDto?
    ) {
        if (currentWeatherDto!=null){
            weatherLocalDataSource.updateCurrentWeather(currentWeatherDto.toCurrentWeatherLocal())
        }

        if (foreCastWeatherDto!=null){
            val numberOfDays = foreCastWeatherDto.forecast.forecastday.size
            for(day in 0..numberOfDays){
                val data = foreCastWeatherDto.toForecastLocal(day)
                weatherLocalDataSource.addForecast(data.first)
                weatherLocalDataSource.addForecastHours(data.second)
            }
        }
    }

    override suspend fun dataUpdate(location: String) {
        val currentWeatherResponse = weatherRemoteDataSource.getCurrentWeather(location=location,"yes")
        val forecastWeatherUpdate = weatherRemoteDataSource.getForeCast(location)

        when(currentWeatherResponse){
            is Result.Error -> {

            }
            is Result.Success -> {
                weatherLocalDataSource.updateCurrentWeather(currentWeatherResponse.data.toCurrentWeatherLocal())
            }
        }

        when(forecastWeatherUpdate){
            is Result.Error -> {

            }
            is Result.Success -> {
                val numberOfDays = forecastWeatherUpdate.data.forecast.forecastday.size
                for(day in 0..<numberOfDays){
                    val data = forecastWeatherUpdate.data.toForecastLocal(day)
                    weatherLocalDataSource.addForecast(data.first)
                    weatherLocalDataSource.addForecastHours(data.second)
                }
            }
        }
    }

    override suspend fun getHourData(date: String): List<Hour> {
        return weatherLocalDataSource.getHours(date)
    }

    override suspend fun getAstroData(date: String): Astro? {
        return weatherLocalDataSource.getWeatherForecast(date)?.astro
    }

    override suspend fun getDayDetails(date: String): Day? {
        return weatherLocalDataSource.getWeatherForecast(date)?.day
    }
}