package com.example.weatherman.presentation.tomorrowScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherman.domain.WeatherRepository
import com.example.weatherman.presentation.components.OnAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TomorrowScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
):ViewModel() {

    private var currentLocation:String=""

    private fun getCurrentLocation(){
        viewModelScope.launch {
            weatherRepository.getCurrentLocation().collect { currentLocation = it.city ?: "Kampala" }
        }
    }

    private fun upDateCurrentLocation(location: String){
        viewModelScope.launch { weatherRepository.addNewLocation(location,43.4,33.5) }
    }

    init {
        getCurrentLocation()
    }

    private val _tomorrowScreenState = MutableStateFlow("")
    val tomorrowScreenState = _tomorrowScreenState.asStateFlow()

    fun onAction(action:OnAction){

    }
}