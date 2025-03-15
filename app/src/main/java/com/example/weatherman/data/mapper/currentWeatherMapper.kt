package com.example.weatherman.data.mapper

import com.example.weatherman.data.remote.dto.CurrentDto
import com.example.weatherman.data.remote.dto.CurrentWeatherDto
import com.example.weatherman.data.remote.dto.ForeCastWeatherDto
import com.example.weatherman.data.remote.dto.ForecastdayDto
import com.example.weatherman.data.remote.dto.HourDto
import com.example.weatherman.domain.models.AirQuality
import com.example.weatherman.domain.models.Astro
import com.example.weatherman.domain.models.Condition
import com.example.weatherman.domain.models.Current
import com.example.weatherman.domain.models.CurrentWeather
import com.example.weatherman.domain.models.Day
import com.example.weatherman.domain.models.ForeCastWeather
import com.example.weatherman.domain.models.Forecast
import com.example.weatherman.domain.models.Forecastday
import com.example.weatherman.domain.models.Hour
import com.example.weatherman.domain.models.Location

fun CurrentWeatherDto.toCurrentWeather():CurrentWeather{

    val airQuality = this.current.getAirQuality()
    val condition = this.current.getCondition()
    val currentDto=this.current.toCurrent(airQuality,condition)
    val locationDto= this.getLocation()

    return CurrentWeather(
        current=currentDto,
        location=locationDto
    )
}

fun CurrentDto.getCondition():Condition{
    return Condition(
        code = condition.code,
        icon = condition.icon,
        text = condition.text,
    )
}

fun  CurrentDto.getAirQuality():AirQuality{
    return AirQuality(
        carbonMonoxide = air_quality.co,
        nitrogenDioxide = air_quality.no2,
        ozone = air_quality.o3,
        sulphurDioxide = air_quality.so2,
    )
}

fun CurrentDto.toCurrent(airQuality: AirQuality,condition: Condition):Current{
    return Current(
        airQuality = airQuality,
        cloud =cloud,
        condition = condition,
        dewPoint = dewpoint_c,
        feelsLike =feelslike_c,
        gust = gust_kph,
        humidity =humidity,
        isDay = is_day,
        lastUpdated =last_updated,
        precipitationInInches = precip_in,
        pressure = pressure_mb,
        temp = temp_c,
        uv = uv,
        visibility = vis_km,
        windDegree = wind_degree,
        windDirection = wind_dir,
        windSpeed = wind_kph,
        windChill = windchill_c
    )
}

fun CurrentWeatherDto.getLocation():Location{
    return Location(
        country = location.country,
        lat = location.lat,
        localtime = location.localtime,
        lon = location.lon,
        name = location.name,
        region = location.region,
        tz_id = location.tz_id
    )
}


fun ForeCastWeatherDto.toForeCastWeather(): ForeCastWeather {
    val airQuality = AirQuality(
        carbonMonoxide = this.current.air_quality.co,
        nitrogenDioxide = this.current.air_quality.no2,
        ozone = this.current.air_quality.o3,
        sulphurDioxide = this.current.air_quality.so2
    )

    val condition =  Condition(
        code =this.current.condition.code,
        icon = this.current.condition.icon,
        text = this.current.condition.text
    )

    val location= Location(
        country = this.locationDto.country,
        lat = this.locationDto.lat,
        localtime = this.locationDto.localtime,
        lon = this.locationDto.lon,
        name = this.locationDto.name,
        region = this.locationDto.region,
        tz_id = this.locationDto.tz_id
    )

    val current = current.toCurrent(airQuality,condition)

    val forecast = Forecast(
        forecastDay = this.forecastDto.forecastdayDto.map { forecastDayDto->
            forecastDayDto.toForeCastDay()
        }
    )

    return ForeCastWeather(
        current = current,
        forecast = forecast,
        location = location
    )
}

fun ForecastdayDto.toForeCastDay():Forecastday{
    val astro = Astro(
        isMoonUp = this.astroDto.is_moon_up !=0 ,
        isSunUp = this.astroDto.is_sun_up != 0,
        moonIllumination = this.astroDto.moon_illumination,
        moonPhase = this.astroDto.moon_phase,
        moonrise = this.astroDto.moonrise,
        moonSet = this.astroDto.moonset,
        sunrise = this.astroDto.sunrise,
        sunset = this.astroDto.sunset
    )
    val condition =  Condition(
        code =this.dayDto.conditionDto.code,
        icon = this.dayDto.conditionDto.icon,
        text = this.dayDto.conditionDto.text
    )

    val day = Day(
        avgHumidity = this.dayDto.avghumidity,
        avgTemperature = this.dayDto.avgtemp_c,
        avgVisibility = this.dayDto.avgvis_km,
        condition = condition,
        chanceOfRain = this.dayDto.daily_chance_of_rain,
        chanceOfSnow = this.dayDto.daily_chance_of_snow,
        willItRain = this.dayDto.daily_will_it_rain!=0,
        willItSnow = this.dayDto.daily_will_it_snow !=0,
        maxTemperature = this.dayDto.maxtemp_c,
        maxWind = this.dayDto.maxwind_kph,
        minTemperature = this.dayDto.mintemp_c,
        totalPrecipitation = this.dayDto.totalprecip_in,
        totalSnow = this.dayDto.totalprecip_in,
        uv = this.dayDto.uv
    )


    return Forecastday(
        astro = astro,
        date = this.date,
        day = day,
        hour = this.hourDto.map { hourDto->
                hourDto.toHour()
        }
    )

}

fun HourDto.toHour():Hour{
    return Hour(
        chanceOfRain = chance_of_rain,
        chanceOfSnow = chance_of_snow,
        cloud = cloud,
        condition = Condition(
            code = this.conditionDto.code,
            icon = this.conditionDto.icon,
            text = this.conditionDto.text
        ),
        feelsLike = feelslike_c,
        windStrength = gust_kph,
        humidity = humidity,
        is_day = is_day,
        precipitationInInches = precip_in,
        pressureInInches = pressure_in,
        snowInCm = snow_cm,
        temperatureInC = temp_c,
        time = time,
        uv = uv,
        visualInKm = vis_km,
        willItRain = will_it_rain,
        willItSnow = will_it_snow,
        windDegree = wind_degree,
        windDirection = wind_dir,
        windInKph = wind_kph,
        windChill = windchill_c
    )
}