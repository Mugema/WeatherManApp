package com.example.weatherman.presentation.homeScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherman.presentation.components.ActionBar
import com.example.weatherman.presentation.components.OnAction
import com.example.weatherman.presentation.components.SelectedScreen
import com.example.weatherman.presentation.components.Time
import com.example.weatherman.presentation.components.TopBar
import com.example.weatherman.presentation.homeScreen.components.AirQuality
import com.example.weatherman.presentation.homeScreen.components.DetailsCard
import com.example.weatherman.presentation.models.AirQualityUi
import com.example.weatherman.presentation.models.Pressure
import com.example.weatherman.presentation.models.Temperature
import com.example.weatherman.presentation.models.Uv
import com.example.weatherman.presentation.models.Wind
import com.example.weatherman.presentation.tomorrowScreen.components.AstroDetails
import com.example.weatherman.presentation.tomorrowScreen.components.sunSet1
import com.example.weatherman.ui.theme.WeatherManTheme


@Composable
fun HomeScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel= hiltViewModel(),
    navigateToTomorrowScreen: ()->Unit={},
    navigateToForecastScreen: () -> Unit={}
) {
    val state = viewModel.homeScreenState.collectAsStateWithLifecycle()
    HomeScreen(
        modifier = modifier,
        homeScreenState = state.value,
        navigateToForecastScreen = navigateToForecastScreen,
        navigateToTomorrowScreen = navigateToTomorrowScreen,
        onAction = viewModel::action,
    )
}

@Composable
fun HomeScreen(
    homeScreenState: HomeScreenUiState,
    modifier: Modifier = Modifier,
    navigateToTomorrowScreen: ()->Unit={},
    navigateToForecastScreen: () -> Unit={},
    onAction: (OnAction)->Unit
) {
    val scrollState= rememberScrollState()
    var scrolled by remember{ mutableIntStateOf(0) }

    scrolled = if (scrollState.value==0) 0 else 1

    val textColor = Color.Black

    Column(
        modifier=modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .clip(if (scrolled==0) RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp) else RectangleShape)
                .animateContentSize()
                .background(Color.LightGray.copy(alpha = 1f, green = 1f))
        ) {
            AnimatedContent(
                targetState = scrolled
            ) { state->
                if(state==0){
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ActionBar(location =homeScreenState.location , isOnline = true, onAction = onAction)
                        Spacer(modifier
                            .padding(8.dp)
                        )
                        Text("Partly Cloudy", fontSize = 20.sp)
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                                .padding(16.dp)
                        ){
                            AstroDetails(modifier = Modifier.weight(1f).clip(RoundedCornerShape(100)),astroState = sunSet1)
                            Spacer(Modifier.width(8.dp))
                            AstroDetails(modifier = Modifier.weight(1f).clip(RoundedCornerShape(100)),astroState = sunSet1)
                        }
                    }
                }
                else{
                    TopBar(
                        location = homeScreenState.location,
                        isOnline = true,
                        onAction = onAction,
                        toTomorrowScreen = navigateToTomorrowScreen,
                        toForeCastScreen = navigateToForecastScreen
                    )
                }
            }
        }
        Spacer(modifier=Modifier.height(4.dp))
        AnimatedVisibility(visible = homeScreenState.isLoading) {
            LinearProgressIndicator(modifier=Modifier.fillMaxWidth())
        }

        Column(
            Modifier
                .padding(12.dp)
                .weight(0.7F)
                .verticalScroll(scrollState)
        ) {
            Time(
                modifier = Modifier.fillMaxWidth(),
                onTomorrowClicked = navigateToTomorrowScreen,
                onForeCastClicked = navigateToForecastScreen,
                selected = SelectedScreen(true,false,false)
            )
            Spacer(modifier=Modifier.height(16.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(4.dp),
            ) {
                item {
                    DetailsCard(modifier=Modifier,state = homeScreenState.temperature, title = "Temp")
                }

                item{
                    DetailsCard(modifier=Modifier,state = homeScreenState.wind, title = "Wind")
                }
                item{
                    DetailsCard(modifier=Modifier,state = homeScreenState.pressure, title = "Pressure")
                }
                item{
                    DetailsCard(modifier=Modifier,state = homeScreenState.uv, title = "UV Index")
                }
            }
            Spacer(modifier=Modifier.height(16.dp))
            HourlyForeCast()
            Spacer(modifier=Modifier.height(16.dp))
            AirQuality(modifier=Modifier,airQualityUi = homeScreenState.air)
            Spacer(modifier=Modifier.height(16.dp))
        }

    }

}

val state = HomeScreenUiState(
    location = "Kampala,Uganda",
    sunset = "6:25AM",
    sunrise = "6:25PM",
    time = "17:10",
    air = AirQualityUi(0.4f,0.1f,0.5f,0.0f),
    wind = Wind(attrValue = "89.0", change = -7),
    temperature = Temperature(attrValue = "19.9", change = -2),
    pressure = Pressure(attrValue = "45.0", change = 9),
    uv = Uv(attrValue = "6.0")
)


@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun HomeScreenPreview() {
    WeatherManTheme {
        HomeScreen(
            homeScreenState = state,
        ){}
    }

}

