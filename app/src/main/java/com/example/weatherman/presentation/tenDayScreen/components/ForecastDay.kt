package com.example.weatherman.presentation.tenDayScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherman.R
import com.example.weatherman.ui.theme.WeatherManTheme
import com.example.weatherman.ui.theme.primaryColor

@Composable
fun ForecastDay(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier=modifier
            .clip(RoundedCornerShape(10))
            .fillMaxWidth()
            .background(primaryColor)
            .padding(16.dp)
    ) {
        Image(modifier = Modifier.size(64.dp),
            painter = painterResource(R.drawable.sun_1_),
            contentDescription = null)
        Column {
            Text("Monday,February 2025", fontSize = 20.sp)
            Text("Partly Cloudy")
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
        ForecastDay()
    }

}