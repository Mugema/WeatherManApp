package com.example.weatherman.presentation.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherman.domain.Result
import com.example.weatherman.domain.WeatherRepository
import com.example.weatherman.domain.mapper.toAirQualityUi
import com.example.weatherman.presentation.models.AirQualityUi
import com.example.weatherman.presentation.models.Pressure
import com.example.weatherman.presentation.models.Temperature
import com.example.weatherman.presentation.models.Uv
import com.example.weatherman.presentation.models.Wind
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
):ViewModel() {

    private val _homeScreenState = MutableStateFlow(HomeScreenUiState())
    val homeScreenState=_homeScreenState.onStart {
        getWeatherData("Kampala")
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        HomeScreenUiState()
    )

     private fun getWeatherData(location: String){
         _homeScreenState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            weatherRepository.getCurrentWeather(location).collect{weatherData->
                when(weatherData){
                    is Result.Error -> {
                        Log.d("getWeatherData","Encoutered and error")

                    }
                    is Result.Success -> {
                        val data = weatherData.data
                        _homeScreenState.update { it.copy(
                            location = "${data.location.name},${data.location.country}",
                            sunrise = "6:25PM",
                            sunset = "6:25AM",
                            time = data.location.localtime,
                            air = data.current.airQuality.toAirQualityUi(),
                            wind = Wind(attrValue = "${data.current.windSpeed}Kph"),
                            temperature = Temperature(attrValue ="${data.current.temp}°C"),
                            pressure = Pressure(attrValue = "${data.current.pressure} Kpa"),
                            uv =  Uv(attrValue = data.current.uv.toString())
                        ) }
                        _homeScreenState.update { it.copy(isLoading = false) }
                    }
                }
            }
        }
    }
}

data class HomeScreenUiState(
    val isLoading:Boolean=true,
    val location:String="",
    val sunset:String="",
    val sunrise:String="",
    val time:String="",
    val air:AirQualityUi=AirQualityUi(),
    val wind:Wind=Wind(attrValue = "0.0"),
    val temperature: Temperature=Temperature(attrValue = "0.0"),
    val pressure: Pressure=Pressure(attrValue = "0.0"),
    val uv:Uv= Uv(attrValue = "0.0"),
)