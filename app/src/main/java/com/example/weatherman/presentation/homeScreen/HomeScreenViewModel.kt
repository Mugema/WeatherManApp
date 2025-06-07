package com.example.weatherman.presentation.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherman.domain.Result
import com.example.weatherman.domain.WeatherRepository
import com.example.weatherman.presentation.components.OnAction
import com.example.weatherman.presentation.mapper.toAirQualityUi
import com.example.weatherman.presentation.models.AirQualityUi
import com.example.weatherman.presentation.models.Pressure
import com.example.weatherman.presentation.models.Temperature
import com.example.weatherman.presentation.models.Uv
import com.example.weatherman.presentation.models.Wind
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
    private var _currentLocation=""
    private var searchQuery=_currentLocation

    init {
        getCurrentLocation()
    }

    private fun getCurrentLocation(){
        viewModelScope.launch {
            Log.d("Data Store","${weatherRepository.getCurrentLocation()}")
            weatherRepository.getCurrentLocation().collect { _currentLocation = it.city  }
            searchQuery = _currentLocation
        }
    }
    private fun cleanLocation(location:String):String{
        return location.replaceFirstChar { it.titlecaseChar() }
    }

    private fun upDateCurrentLocation(location: String,lat: Double,lon: Double){
        viewModelScope.launch {
            weatherRepository.addNewLocation(location=cleanLocation(location),lat=lat,lon = lon)
            Log.d("CurrentLocation ",_currentLocation)
        }
    }

    private val _homeScreenState = MutableStateFlow(HomeScreenUiState())
    val homeScreenState=_homeScreenState.onStart {
        delay(2000L)
        Log.d("onStart ", "Before getWeather $searchQuery")
        getWeatherData(searchQuery)
        Log.d("onStart ", "After getWeather")
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        HomeScreenUiState()
    )

    fun action(action: OnAction){
        when(action){
            is OnAction.OnSearch -> {
                _homeScreenState.update { it.copy(location =action.location ) }
                searchQuery=cleanLocation(action.location)
            }
            is OnAction.MakeSearch -> { getWeatherData(searchQuery) }
        }
    }

     private fun getWeatherData(location: String){
         _homeScreenState.update { it.copy(isLoading = true) }
         viewModelScope.launch {
            Log.d("getWeatherData","Location:$location")
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
                            air = data.airQuality.toAirQualityUi(),
                            wind = Wind(attrValue = "${data.windSpeed}Kph"),
                            temperature = Temperature(attrValue ="${data.temp}°C"),
                            pressure = Pressure(attrValue = "${data.pressure} Kpa"),
                            uv =  Uv(attrValue = data.uv.toString())
                        ) }
                        upDateCurrentLocation(
                            location,
                            lat =data.location.lat,
                            lon = data.location.lon
                        )
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