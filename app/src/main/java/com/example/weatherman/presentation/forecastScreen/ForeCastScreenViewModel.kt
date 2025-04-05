package com.example.weatherman.presentation.forecastScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherman.domain.WeatherRepository
import com.example.weatherman.presentation.components.OnAction
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private fun getCurrentLocation(){
        viewModelScope.launch {
            currentLocation = weatherRepository.getCurrentLocation() ?: "Kampala"
        }
    }

    private fun upDateCurrentLocation(location: String){
        viewModelScope.launch { weatherRepository.addNewLocation(location) }
    }

    init {
        getCurrentLocation()
    }

    private val _forecastScreenState = MutableStateFlow(ForecastScreenState())
    val state = _forecastScreenState.onStart {

    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        null
    )

}



data class ForecastScreenState(
    val isLoading:Boolean=false,
    val location: String="",
    val icon:String="",
    val conditionDescription:String="",
    val date:String=""
)