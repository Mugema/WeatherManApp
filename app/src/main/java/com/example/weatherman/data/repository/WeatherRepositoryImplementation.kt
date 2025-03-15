package com.example.weatherman.data.repository

import com.example.weatherman.data.remote.WeatherOnlineDataSource
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
    private val weatherLocalDataSource: WeatherOnlineDataSource,
    private val ioDispatcher: CoroutineDispatcher
):WeatherRepository {
    override suspend fun getCurrentWeather(location:String): Flow<Result<CurrentWeather, DataErrors.RemoteError>> {
        return flow {
            val response = weatherRemoteDataSource.getCurrentWeather(location=location,airQuality = "yes")
            emit(response)
        }.flowOn(ioDispatcher)
    }

    override suspend fun getForeCastWeather(location: String): Flow<Result<ForeCastWeather, DataErrors.RemoteError>> {
       return flow {
           val foreCastResponse = weatherRemoteDataSource.getForeCast(location,"yes")
           emit(foreCastResponse)
       }.flowOn(ioDispatcher)
    }

}