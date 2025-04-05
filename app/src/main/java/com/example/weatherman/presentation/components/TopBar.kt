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
fun TopBar(
    modifier: Modifier = Modifier,
    location: String,
    isOnline: Boolean,
    toHomeScreen:()->Unit={},
    toTomorrowScreen:()->Unit={},
    toForeCastScreen:()->Unit={},
    selected: SelectedScreen = SelectedScreen(),
    onAction:(OnAction)->Unit
) {
    Column(modifier =modifier.background(Color.LightGray.copy(alpha = 1f, green = 1f))
    ){
        ActionBar(
            location = location,
            isOnline = isOnline,
            onAction = onAction
        )
        Spacer(Modifier.height(8.dp))
        Time(
            selected = selected,
            onTodayClicked = toHomeScreen,
            onForeCastClicked = toForeCastScreen,
            onTomorrowClicked = toTomorrowScreen
        )
        Spacer(modifier = Modifier.height(8.dp))
    }

}

@Preview
@Composable
private fun TopBarPreview() {
    WeatherManTheme { TopBar(location = "Washington,USA", isOnline = true){} }

}