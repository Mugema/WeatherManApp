package com.example.weatherman.presentation.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherman.R
import com.example.weatherman.presentation.homeScreen.components.ForeCast
import com.example.weatherman.presentation.homeScreen.components.dd
import com.example.weatherman.ui.theme.WeatherManTheme
import com.example.weatherman.ui.theme.primaryColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HourlyForeCast(modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier
            .clip(RoundedCornerShape(5))
            .background(primaryColor)
            .padding(16.dp)
            .fillMaxWidth()
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.Start)
        ) {
            Icon( painter = painterResource(R.drawable.clock),
                contentDescription = null,
                modifier=Modifier.width(32.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(4.dp)
                )
            Text("Hourly forecast",
                modifier.padding(start = 8.dp),
                fontSize = 24.sp)
        }
        FlowRow(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(4){
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