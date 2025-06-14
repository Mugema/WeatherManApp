package com.example.weatherman.data.remote

import com.example.weatherman.BuildConfig
import com.example.weatherman.data.mapper.toCurrentWeather
import com.example.weatherman.data.mapper.toForeCastWeather
import com.example.weatherman.data.remote.dto.CurrentWeatherDto
import com.example.weatherman.data.remote.dto.ForeCastWeatherDto
import com.example.weatherman.data.remote.util.safeCall
import com.example.weatherman.domain.DataErrors
import com.example.weatherman.domain.Result
import com.example.weatherman.domain.map
import com.example.weatherman.domain.models.CurrentWeather
import com.example.weatherman.domain.models.ForeCastWeather
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import javax.inject.Inject

class WeatherOnlineDataSource @Inject constructor(
    private val client:HttpClient
):WeatherService {
    private val API_KEY= BuildConfig.ApiKey

    override suspend fun getCurrentWeather(
        location: String,
        airQuality: String
    ):Result<CurrentWeather,DataErrors.RemoteError>
    {
        return safeCall<CurrentWeatherDto> {
            client.request {
                url(WeatherRoutes.CURRENT_WEATHER)
                parameter("key",API_KEY)
                parameter("q", location)
                parameter("aqi",airQuality)
                method= HttpMethod.Get
            }
        }.map { response ->
            response.toCurrentWeather()
        }
    }

    override suspend fun getForeCast(
        location: String,
        airQuality: String,
        days: Int
    ): Result<ForeCastWeather, DataErrors.RemoteError> {
        return safeCall<ForeCastWeatherDto> {
            client.request{
                url(WeatherRoutes.FORECAST_WEATHER)
                parameter("key",API_KEY)
                parameter("q",location)
                parameter("aqi",airQuality)
                parameter("days",days)
                method= HttpMethod.Get
            }
        }.map { response ->
            response.toForeCastWeather()
        }
    }
}