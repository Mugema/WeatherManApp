package com.example.weatherman.data.mapper

import com.example.weatherman.data.local.models.AirQualityLocal
import com.example.weatherman.data.local.models.AstroLocal
import com.example.weatherman.data.local.models.ConditionLocal
import com.example.weatherman.data.local.models.CurrentWeatherLocal
import com.example.weatherman.data.local.models.DayLocal
import com.example.weatherman.data.local.models.ForecastLocal
import com.example.weatherman.data.local.models.HourLocal
import com.example.weatherman.data.local.models.LocationLocal
import com.example.weatherman.domain.models.AirQuality
import com.example.weatherman.domain.models.Astro
import com.example.weatherman.domain.models.Condition
import com.example.weatherman.domain.models.CurrentWeather
import com.example.weatherman.domain.models.Day
import com.example.weatherman.domain.models.ForeCastWeather
import com.example.weatherman.domain.models.Hour
import com.example.weatherman.domain.models.Location

fun CurrentWeatherLocal.toCurrentWeather(): CurrentWeather {
    val location = this.locationLocal.toLocation()
    return CurrentWeather(
     location = location,
     airQuality = airQuality.toAirQuality(),
     cloud =cloud,
     condition = condition.toCondition(),
     feelsLike = feelsLike,
     gust = gust,
     humidity =humidity,
     isDay = isDay,
     lastUpdated = lastUpdated,
     precipitationInInches = precipitationInInches,
     pressure = pressure,
     temp = temp,
     uv = uv,
     visibility = visibility,
     windDegree = windDegree,
     windDirection = windDirection,
     windSpeed = windSpeed,
     windChill = windChill,
    )
}

fun ConditionLocal.toCondition(): Condition {
    return Condition(
        date = code.toString(),
        icon = code,
        text = text,
    )
}

fun AirQualityLocal.toAirQuality(): AirQuality {
    return AirQuality(
        carbonMonoxide = carbonMonoxide,
        nitrogenDioxide = nitrogenDioxide,
        ozone = ozone,
        sulphurDioxide = sulphurDioxide,
    )
}

fun LocationLocal.toLocation(): Location {
    return Location(
        country = country,
        lat = lat,
        localtime = localtime,
        lon = lon,
        name = name,
        region = region,
        tz_id = tz_id
    )
}

fun ForecastLocal.toForecastWeather(hours:List<Hour>):ForeCastWeather{
    return ForeCastWeather(
        date = this.date,
        localLocation = this.localLocation.toLocation(),
        astro = this.astro.toAstro(),
        day = this.day.toDay(),
        hours = hours
    )
}

fun HourLocal.toHour():Hour{
    return Hour(
        chanceOfRain = this.chanceOfRain,
        chanceOfSnow = this.chanceOfSnow,
        cloud = this.cloud,
        condition = this.condition.toCondition(),
        feelsLike = this.feelsLike,
        windStrength = this.windStrength,
        humidity = this.humidity,
        is_day = this.is_day,
        precipitationInInches = this.precipitationInInches,
        pressureInInches = this.pressureInInches,
        snowInCm = this.snowInCm,
        temperatureInC = this.temperatureInC,
        time = this.time,
        uv = this.uv,
        visualInKm = this.visualInKm,
        willItRain = this.willItRain,
        willItSnow = this.willItSnow,
        windDegree = this.windDegree,
        windDirection = this.windDirection,
        windInKph = this.windInKph,
        windChill = this.windChill,
        lon = this.lon,
        lat = this.lat
    )
}

fun AstroLocal.toAstro():Astro{
    return Astro(
        isMoonUp = this.isMoonUp,
        isSunUp = this.isSunUp,
        moonIllumination = this.moonIllumination,
        moonPhase = this.moonPhase,
        moonrise = this.moonrise,
        moonSet = this.moonSet,
        sunrise = this.sunrise,
        sunset = this.sunset
    )
}

fun DayLocal.toDay():Day{
    return Day(
        avgHumidity = this.avgHumidity,
        avgTemperature = this.avgTemperature,
        avgVisibility = this.avgVisibility,
        condition = this.condition.toCondition(),
        chanceOfRain = this.chanceOfRain,
        chanceOfSnow = this.chanceOfSnow,
        willItRain = this.willItRain,
        willItSnow = this.willItSnow,
        maxTemperature = this.maxTemperature,
        maxWind = this.maxWind,
        minTemperature = this.minTemperature,
        totalPrecipitation = this.totalPrecipitation,
        totalSnow = this.totalSnow,
        uv = this.uv
    )
}