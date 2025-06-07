package com.example.weatherman.presentation.forecastScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherman.domain.Result
import com.example.weatherman.domain.WeatherRepository
import com.example.weatherman.domain.models.DataStoreLocation
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
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class ForeCastScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
):ViewModel() {

    private var currentLocation = DataStoreLocation()
    private var searchQuery = currentLocation.city

    init {
        getCurrentLocation()
    }

    private fun getCurrentLocation(){
        viewModelScope.launch {
            weatherRepository.getCurrentLocation().collect { data->
                currentLocation =data
            }
            searchQuery = currentLocation.city
            Log.d("Forecast","The location is $currentLocation")
            Log.d("Forecast","The searchQuery is $searchQuery")
        }
    }
    private fun cleanLocation(location:String):String{
        return location.replaceFirstChar { it.titlecaseChar() }
    }

    private fun upDateCurrentLocation(location: String,lat: Double,lon: Double){
        viewModelScope.launch {
            weatherRepository.addNewLocation(location=cleanLocation(location),lat=lat,lon = lon)
            Log.d("CurrentLocation ",currentLocation.toString())
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
                        Log.d("Forecast Screen viewmodel","Encountered and error ${forecast.error.name}")
                    }
                    is Result.Success ->
                        {
                        _forecastScreenState.update { it.copy(
                            location = "${forecast.data.localLocation.name},${forecast.data.localLocation.country}",
                            isLoading = false)}
                            upDateCurrentLocation(
                                location = forecast.data.localLocation.name,
                                lon = forecast.data.localLocation.lon,
                                lat = forecast.data.localLocation.lat
                            )
                        }
                }
            }
        }
    }

    suspend fun makeDayState(){
        _forecastScreenState.update { it.copy(isLoading = true) }

        weatherRepository.getForecastConditions().collect { condition ->
            _forecastScreenState.update { it.copy(
                isLoading = false,
                days = condition.map {
                    DayState(
                        icon = it.icon,
                        day = formatDate(it.date),
                        condition = it.text
                    ) }
            ) }
            }
        }


    @SuppressLint("SimpleDateFormat")
    fun formatDate(dateString: String): String {
        val date = SimpleDateFormat("yyyy-MM-dd").parse(dateString)
        return SimpleDateFormat("MMM dd, yyyy").format(date)
    }

}


data class ForecastScreenState(
    val isLoading:Boolean=true,
    val location: String="",
    val days:List<DayState> = listOf()
)

