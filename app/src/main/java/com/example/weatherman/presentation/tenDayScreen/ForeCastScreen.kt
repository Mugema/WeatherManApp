package com.example.weatherman.presentation.tenDayScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherman.presentation.components.SelectedScreen
import com.example.weatherman.presentation.components.TopBar
import com.example.weatherman.presentation.tenDayScreen.components.ForecastDay
import com.example.weatherman.ui.theme.WeatherManTheme

@Composable
fun ForeCastScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: ForeCastScreenViewModel= hiltViewModel(),
    navigateToTomorrowScreen: ()->Unit={},
    navigateToHomeScreen: () -> Unit={}
) {
    ForeCastScreen(
        navigateToTomorrowScreen = navigateToTomorrowScreen,
        navigateToHomeScreen = navigateToHomeScreen
    )
}

@Composable
fun ForeCastScreen(
    modifier: Modifier = Modifier,
    navigateToTomorrowScreen: ()->Unit={},
    navigateToHomeScreen: () -> Unit={},
) {
    Column(
        modifier=modifier.fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(
            location = "Kampala,Uganda",
            isOnline = false,
            toTomorrowScreen = navigateToTomorrowScreen,
            toHomeScreen = navigateToHomeScreen,
            selected = SelectedScreen(false,false,true)
        )
        Spacer(modifier=Modifier.height(4.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            item {  }
            items(10){
                ForecastDay(modifier=Modifier.padding(start = 8.dp, end = 8.dp))
            }
        }
    }

}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun PreviewForecastScreen(){
    WeatherManTheme {
        ForeCastScreen(){}
    }
}
