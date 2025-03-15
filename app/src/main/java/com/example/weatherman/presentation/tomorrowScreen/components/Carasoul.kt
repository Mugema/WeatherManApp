package com.example.weatherman.presentation.tomorrowScreen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherman.presentation.homeScreen.HomeScreenUiState
import com.example.weatherman.ui.theme.WeatherManTheme

@Composable
fun carasoul(modifier: Modifier = Modifier,homeScreenState: HomeScreenUiState) {

}

@Preview
@Composable
private fun PreviewCarasoul() {
    WeatherManTheme {
        carasoul(homeScreenState = HomeScreenUiState())
    }

}