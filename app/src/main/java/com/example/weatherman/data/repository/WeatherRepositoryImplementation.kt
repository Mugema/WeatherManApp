package com.example.weatherman.data.repository

import com.example.weatherman.data.local.WeatherLocalDataSource
import com.example.weatherman.data.mapper.toCurrentWeatherLocal
import com.example.weatherman.data.mapper.toForecastLocal
import com.example.weatherman.data.mapper.toHourLocal
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
        val response = weatherRemoteDataSource.getCurrentWeather(location=location,airQuality = "yes")
        when(response){
            is Result.Error -> {
                return flow {
                    emit(response)
                }.flowOn(ioDispatcher)
            }
            is Result.Success -> {
                dataSynchronization(response.data)
                return flow {
                    emit(Result.Success(weatherLocalDataSource.getCurrentWeather()))
                }.flowOn(ioDispatcher)
            }
        }
    }

    override suspend fun getForeCastWeather(location: String): Flow<Result<ForeCastWeather, DataErrors.RemoteError>> {
        val response = weatherRemoteDataSource.getForeCast(location,"no")
        when(response){
            is Result.Error -> {
                return flow {
                    emit(response)
                }.flowOn(ioDispatcher)
            }
            is Result.Success -> {
                dataSynchronization(foreCastWeatherDto = response.data)
                return flow{
                    emit(Result.Success(weatherLocalDataSource.getWeatherForecast()))
                }.flowOn(ioDispatcher)
            }
        }
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
}