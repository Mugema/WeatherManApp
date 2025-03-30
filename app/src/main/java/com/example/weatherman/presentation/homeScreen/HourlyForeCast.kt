package com.example.weatherman.presentation.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherman.presentation.homeScreen.components.ForeCast
import com.example.weatherman.presentation.homeScreen.components.dd
import com.example.weatherman.ui.theme.WeatherManTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HourlyForeCast(modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ){

        Text("Hourly forecast",
            modifier.padding(start = 8.dp),
            fontSize = 24.sp)


        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(4){
                ForeCast(modifier=Modifier.weight(0.25f),state = dd)
            }

        }
    }
}

@Preview
@Composable
private fun HourlyForecastPreview() {
    WeatherManTheme {
        HourlyForeCast()
    }
    
}