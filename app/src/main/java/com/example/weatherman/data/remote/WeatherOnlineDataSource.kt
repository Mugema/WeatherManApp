package com.example.weatherman.data.remote

import com.example.weatherman.data.tempMapper.toForeCastWeather
import com.example.weatherman.data.remote.dto.CurrentWeatherDto
import com.example.weatherman.data.remote.dto.ForeCastWeatherDto
import com.example.weatherman.data.remote.util.safeCall
import com.example.weatherman.domain.DataErrors
import com.example.weatherman.domain.Result
import com.example.weatherman.domain.map
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import javax.inject.Inject

class WeatherOnlineDataSource @Inject constructor(
    private val client:HttpClient
):WeatherService {
    private val API_KEY="193403fa4f32439fa0d130755252901"

    override suspend fun getCurrentWeather(
        location: String,
        airQuality: String
    ):Result<CurrentWeatherDto,DataErrors.RemoteError>
    {
        return safeCall<CurrentWeatherDto> {
            client.request {
                url(WeatherRoutes.CURRENT_WEATHER)
                parameter("key",API_KEY)
                parameter("q", location)
                parameter("aqi",airQuality)
                method= HttpMethod.Get
            }
        }
    }

    override suspend fun getForeCast(
        location: String,
        airQuality: String,
        days: Int
    ): Result<ForeCastWeatherDto, DataErrors.RemoteError> {
        return safeCall<ForeCastWeatherDto> {
            client.request{
                url(WeatherRoutes.FORECAST_WEATHER)
                parameter("key",API_KEY)
                parameter("q",location)
                parameter("aqi",airQuality)
                parameter("days",days)
                method= HttpMethod.Get
            }
        }
    }
}