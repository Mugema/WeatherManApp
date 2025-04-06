package com.example.weatherman.presentation.forecastScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherman.domain.Result
import com.example.weatherman.domain.WeatherRepository
import com.example.weatherman.presentation.components.ForeScreenAction
import com.example.weatherman.presentation.components.OnAction
import com.example.weatherman.presentation.forecastScreen.components.DayState
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
class ForeCastScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
):ViewModel() {

    private var currentLocation:String=""
    private var searchQuery = currentLocation

    init {
        getCurrentLocation()
    }

    private fun getCurrentLocation(){
        viewModelScope.launch {
            currentLocation = weatherRepository.getCurrentLocation() ?: "Kampala"
            searchQuery = currentLocation
            Log.d("Forecast","The location is $currentLocation")
            Log.d("Forecast","The searchQuery is $searchQuery")

        }
    }
    private fun cleanLocation(location:String):String{
        return location.replaceFirstChar { it.titlecaseChar() }
    }

    private fun upDateCurrentLocation(location: String){
        viewModelScope.launch {
            weatherRepository.addNewLocation(cleanLocation(location))
            Log.d("ForecastScreen location ",currentLocation)
        }
    }

    private val _forecastScreenState = MutableStateFlow(ForecastScreenState())
    val state = _forecastScreenState.onStart {
        delay(2000L)
        Log.d("ForecastScreen","OnStart searchQuery is $searchQuery")
        getWeatherForecast(searchQuery)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        ForecastScreenState()
    )

    fun actionBarEvent(action:OnAction){
        when(action){
            is OnAction.OnSearch -> {
                _forecastScreenState.update { it.copy(location =action.location ) }
                searchQuery=cleanLocation(action.location)
            }
            is OnAction.MakeSearch -> { getWeatherForecast(searchQuery) }
        }
    }

    fun forecastScreenAction(action: ForeScreenAction){
        when(action){
            is ForeScreenAction.OnDayClick -> { }
        }
    }

    private fun getWeatherForecast(location:String){
        _forecastScreenState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            weatherRepository.getForeCastWeather(location).collect{forecast->
                when(forecast){
                    is Result.Error -> {
                        Log.d("Forecast Screen viewmodel","Encoutered and error ${forecast.error.name}")
                    }
                    is Result.Success ->
                        {
                        _forecastScreenState.update { it.copy(
                            location = "${forecast.data.localLocation.name},${forecast.data.localLocation.country}",
                            isLoading = false)}
                        }
                }
            }
        }
    }

    fun makeDayState(){

    }

}



data class ForecastScreenState(
    val isLoading:Boolean=false,
    val location: String="",
    //val days:List<DayState> = mutableListOf()
)