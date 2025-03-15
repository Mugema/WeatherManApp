package com.example.weatherman.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherman.ui.theme.WeatherManTheme
import com.example.weatherman.ui.theme.primaryColor

@Composable
fun Rain(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(10))
            .background(primaryColor)
    ) {
        Text(
            "Chance of rain",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
        RainChance(time = "7PM", percentage = 50)
        RainChance(time = "8PM", percentage = 70)
        RainChance(time = "9PM", percentage = 10)
        RainChance(time = "10PM", percentage = 50)
        Spacer(modifier.height(8.dp))
    }
}

@Preview
@Composable
private fun RainPreview() {
    WeatherManTheme {
        Rain()
    }
    
}