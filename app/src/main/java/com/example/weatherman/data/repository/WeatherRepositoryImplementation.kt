package com.example.weatherman.data.repository

import android.util.Log
import com.example.weatherman.data.local.WeatherLocalDataSource
import com.example.weatherman.data.mapper.toCurrentWeatherLocal
import com.example.weatherman.data.mapper.toForecastLocal
import com.example.weatherman.data.remote.WeatherOnlineDataSource
import com.example.weatherman.data.remote.dto.CurrentWeatherDto
import com.example.weatherman.data.remote.dto.ForeCastWeatherDto
import com.example.weatherman.domain.DataErrors
import com.example.weatherman.domain.Result
import com.example.weatherman.domain.WeatherRepository
import com.example.weatherman.domain.models.CurrentWeather
import com.example.weatherman.domain.models.ForeCastWeather
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
        Log.d("RepoImpl","Beginning getCurrentWeather")
        val local = weatherLocalDataSource.getCurrentWeather(location)
        Log.d("RepoImpl",local.toString())
        if (local == null) {
            val response =
                weatherRemoteDataSource.getCurrentWeather(location = location, airQuality = "yes")
            Log.d("imp",response.toString())
            when (response) {
                is Result.Error -> {
                    Log.d("imp","Error")
                    return flow { emit(response) }.flowOn(ioDispatcher)
                }

                is Result.Success -> {
                    Log.d("impl","Success")
                    dataSynchronization(currentWeatherDto = response.data)
                    val remote = weatherLocalDataSource.getCurrentWeather(location)
                    Log.d("Local value ->","$remote")
                    return flow{ emit(Result.Success(remote!!)) }.flowOn(ioDispatcher)
                }
            }
        }
        else{
            return flow { emit(Result.Success(local)) }.flowOn(ioDispatcher)
        }
    }

    override suspend fun getForeCastWeather(location: String): Flow<Result<ForeCastWeather, DataErrors.RemoteError>> {
        var localData = weatherLocalDataSource.getWeatherForecast(location)
        if (localData==null){
            when(val response = weatherRemoteDataSource.getForeCast(location)){
                is Result.Error -> {
                    return flow{ emit(response) }.flowOn(ioDispatcher)
                }
                is Result.Success ->{
                    dataSynchronization(foreCastWeatherDto = response.data)

                    localData = weatherLocalDataSource.getWeatherForecast(location)
                    return flow { emit(Result.Success(localData!!)) }.flowOn(ioDispatcher)

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
            val numberOfDays = foreCastWeatherDto.forecastDto.forecastdayDto.size
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
                val numberOfDays = forecastWeatherUpdate.data.forecastDto.forecastdayDto.size
                for(day in 0..numberOfDays){
                    val data = forecastWeatherUpdate.data.toForecastLocal(day)
                    weatherLocalDataSource.addForecast(data.first)
                    weatherLocalDataSource.addForecastHours(data.second)
                }
            }
        }
    }
}