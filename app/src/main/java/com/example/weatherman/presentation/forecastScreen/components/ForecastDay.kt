package com.example.weatherman.presentation.forecastScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherman.R
import com.example.weatherman.presentation.components.ForeScreenAction
import com.example.weatherman.presentation.components.OnAction
import com.example.weatherman.ui.theme.WeatherManTheme

data class DayState(
    val icon:String="",
    val day:String="Monday,February 2025",
    val condition:String ="Partly Cloudy"
)

@Composable
fun ForecastDay(
    modifier: Modifier = Modifier,
    onClick:(ForeScreenAction)->Unit={},
    dayState:DayState = DayState()
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier=modifier
            .shadow(4.dp, RoundedCornerShape(10), true)
            .clip(RoundedCornerShape(10))
            .clickable {  onClick(ForeScreenAction.OnDayClick(dayState.day)) }
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)

    ) {
        Image(modifier = Modifier.size(64.dp),
            painter = painterResource(R.drawable.sun_1_),
            contentDescription = null)
        Column {
            Text(dayState.day, fontSize = 20.sp)
            Text(dayState.condition)
        }
        IconButton(onClick = {}) {
            Icon(modifier = Modifier.size(32.dp), imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
    }

}

@Preview
@Composable
private fun PreviewForecastDay() {
    WeatherManTheme {
        ForecastDay(dayState = DayState())
    }

}