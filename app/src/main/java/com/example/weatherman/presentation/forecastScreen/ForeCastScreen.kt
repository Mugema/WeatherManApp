package com.example.weatherman.presentation.forecastScreen

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherman.presentation.components.ForeScreenAction
import com.example.weatherman.presentation.components.OnAction
import com.example.weatherman.presentation.components.SelectedScreen
import com.example.weatherman.presentation.components.TopBar
import com.example.weatherman.presentation.forecastScreen.components.ForecastDay
import com.example.weatherman.ui.theme.WeatherManTheme

@Composable
fun ForeCastScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: ForeCastScreenViewModel= hiltViewModel(),
    navigateToTomorrowScreen: ()->Unit={},
    navigateToHomeScreen: () -> Unit={},
) {
    ForeCastScreen(
        modifier = modifier,
        navigateToTomorrowScreen = navigateToTomorrowScreen,
        navigateToHomeScreen = navigateToHomeScreen,
        onAction = viewModel::actionBarEvent,
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onDayClick = viewModel::forecastScreenAction

    )
}

@Composable
fun ForeCastScreen(
    state: ForecastScreenState,
    modifier: Modifier = Modifier,
    navigateToTomorrowScreen: ()->Unit ={},
    navigateToHomeScreen: () -> Unit ={},
    onDayClick: (ForeScreenAction)->Unit ={},
    onAction:(OnAction)->Unit ={}
) {
    Column(
        modifier=modifier.fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(
            isOnline = false,
            toTomorrowScreen = navigateToTomorrowScreen,
            toHomeScreen = navigateToHomeScreen,
            selected = SelectedScreen(false, false, true),
            onAction = onAction,
            location = state.location
        )
        Spacer(modifier=Modifier.height(4.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {  }
            items(3){
                ForecastDay(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    onClick = onDayClick
                )
            }
        }
    }

}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun PreviewForecastScreen(){
    WeatherManTheme {
        ForeCastScreen( state = ForecastScreenState(), onAction = {})
    }
}
