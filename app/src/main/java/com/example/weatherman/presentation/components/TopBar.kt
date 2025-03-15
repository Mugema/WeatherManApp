package com.example.weatherman.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherman.ui.theme.WeatherManTheme

@Composable
fun TopBarRoot(modifier: Modifier = Modifier) {

}

@Composable
fun TopBar(modifier: Modifier = Modifier,location:String,isOnline:Boolean) {
    Column(modifier =modifier.background(Color.LightGray.copy(alpha = 1f, green = 1f))
    ){
        ActionBar(location = location, isOnline = isOnline)
        Spacer(Modifier.height(8.dp))
        Time()
        Spacer(modifier = Modifier.height(16.dp))
    }

}

@Preview
@Composable
private fun TopBarPreview() {
    WeatherManTheme { TopBar(location = "Washington,USA", isOnline = true) }

}