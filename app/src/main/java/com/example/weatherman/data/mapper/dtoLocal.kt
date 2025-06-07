package com.example.weatherman.data.mapper

import com.example.weatherman.data.local.models.AirQualityLocal
import com.example.weatherman.data.local.models.AstroLocal
import com.example.weatherman.data.local.models.ConditionLocal
import com.example.weatherman.data.local.models.CurrentWeatherLocal
import com.example.weatherman.data.local.models.DayLocal
import com.example.weatherman.data.local.models.ForecastLocal
import com.example.weatherman.data.local.models.HourLocal
import com.example.weatherman.data.local.models.LocationLocal
import com.example.weatherman.data.remote.dto.CurrentDto
import com.example.weatherman.data.remote.dto.CurrentWeatherDto
import com.example.weatherman.data.remote.dto.ForeCastWeatherDto
import com.example.weatherman.data.remote.dto.ForecastdayDto
import com.example.weatherman.data.remote.dto.HourDto

fun CurrentWeatherDto.toCurrentWeatherLocal():CurrentWeatherLocal{
    val locationLocal = this.getLocation()
    val airQualityLocal = this.current.toAirQuality()
    val conditionLocal = this.current.getCondition()

    return CurrentWeatherLocal(
        lastUpdatedEpoch = current.last_updated_epoch,
        locationLocal = locationLocal,
        airQuality = airQualityLocal,
        cloud =current.cloud,
        condition = conditionLocal,
        dewPoint = current.dewpoint_c,
        feelsLike = current.feelslike_c,
        gust = current.gust_kph,
        humidity = current.humidity,
        isDay = current.is_day,
        lastUpdated = current.last_updated,
        precipitationInInches = current.precip_in,
        pressure = current.pressure_mb,
        temp = current.temp_c,
        uv = current.uv,
        visibility = current.vis_km,
        windDegree = current.wind_degree,
        windDirection = current.wind_dir,
        windSpeed = current.wind_kph,
        windChill = current.windchill_c
    )
}

fun  CurrentDto.toAirQuality(): AirQualityLocal {
    return AirQualityLocal(
        carbonMonoxide = air_quality.co,
        nitrogenDioxide = air_quality.no2,
        ozone = air_quality.o3,
        sulphurDioxide = air_quality.so2,
    )
}

fun CurrentDto.getCondition(): ConditionLocal {
    return ConditionLocal(
        code = condition.code,
        icon = condition.icon,
        text = condition.text,
    )
}

fun CurrentWeatherDto.getLocation():LocationLocal {
    return LocationLocal(
        country = location.country,
        lat = location.lat,
        localtime = location.localtime,
        lon = location.lon,
        name = location.name,
        region = location.region,
        tz_id = location.tz_id
    )
}

////////////////////////////////////////////ForeCast////////////////////////////////////////////////


fun ForeCastWeatherDto.toForecastLocal(dateNumber:Int): Pair<ForecastLocal,List<HourLocal>> {
    val location= LocationLocal(
        country = this.location.country,
        lat = this.location.lat,
        localtime = this.location.localtime,
        lon = this.location.lon,
        name = this.location.name,
        region = this.location.region,
        tz_id = this.location.tz_id
    )

    val date = this.forecast.forecastday[dateNumber].date
    val astro = this.forecast.forecastday[dateNumber].getAstro()
    val day = this.forecast.forecastday[dateNumber].getDay()
    val hourList:MutableList<HourLocal> = mutableListOf<HourLocal>().also {
        for (hour in 0..23){
            it.add(this.forecast.forecastday[dateNumber].hour[hour].toHourLocal(date, lat = this.location.lat, lon = this.location.lon))
        }
    }

    return Pair(ForecastLocal(
        date = date,
        localLocation = location,
        astro = astro,
        day = day
    ),hourList)
}


fun ForecastdayDto.getAstro():AstroLocal{
    return AstroLocal(
        isMoonUp = this.astro.is_moon_up !=0 ,
        isSunUp = this.astro.is_sun_up != 0,
        moonIllumination = this.astro.moon_illumination,
        moonPhase = this.astro.moon_phase,
        moonrise = this.astro.moonrise,
        moonSet = this.astro.moonset,
        sunrise = this.astro.sunrise,
        sunset = this.astro.sunset
    )
}

fun ForecastdayDto.getDay():DayLocal{

    val condition =  ConditionLocal(
        code =this.dayDto.conditionDto.code,
        icon = this.dayDto.conditionDto.icon,
        text = this.dayDto.conditionDto.text
    )

    return  DayLocal(
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
}

fun HourDto.toHourLocal(date:String,lat: Double,lon: Double,): HourLocal {
    return HourLocal(
        date = date,
        chanceOfRain = chance_of_rain,
        chanceOfSnow = chance_of_snow,
        cloud = cloud,
        condition = ConditionLocal(
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
        windChill = windchill_c,
        lat = lat,
        lon = lon
    )
}