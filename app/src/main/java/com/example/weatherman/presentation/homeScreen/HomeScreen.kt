package com.example.weatherman.presentation.homeScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherman.presentation.components.ActionBar
import com.example.weatherman.presentation.components.Time
import com.example.weatherman.presentation.homeScreen.components.AirQuality
import com.example.weatherman.presentation.homeScreen.components.DetailsCard
import com.example.weatherman.presentation.models.AirQualityUi
import com.example.weatherman.presentation.models.Pressure
import com.example.weatherman.presentation.models.Temperature
import com.example.weatherman.presentation.models.Uv
import com.example.weatherman.presentation.models.Wind
import com.example.weatherman.ui.theme.WeatherManTheme
import com.example.weatherman.ui.theme.accentColor
import com.example.weatherman.ui.theme.baseColor
import com.example.weatherman.ui.theme.primaryLight

@Composable
fun HomeScreenRoot(modifier: Modifier = Modifier,viewModel: HomeScreenViewModel) {
    HomeScreen(homeScreenState =viewModel.homeScreenState.collectAsStateWithLifecycle().value)

}

@Composable
fun HomeScreen(modifier: Modifier = Modifier,homeScreenState: HomeScreenUiState) {
    val scrollState= rememberScrollState()
    var scrolled by remember{ mutableIntStateOf(0) }

    scrolled = if (scrollState.value==0) 0 else 1

    val textColor = Color.Black

    Column(
        modifier=Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .animateContentSize()
                .weight(if (scrolled==0) 0.3f else 0.1f)
                .background(Brush.linearGradient(listOf(Color(85, 145, 127),baseColor, accentColor)))
        ) {
            AnimatedContent(
                targetState = scrolled
            ) { state->
                if(state==0){
                    ActionBar(location =homeScreenState.location , isOnline = true)
                    Spacer(modifier
                        .padding(1.dp)
                        .weight(1f))
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                    ) {
                        Text("January 30,16:30",color = textColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium)
                        Spacer(modifier=Modifier
                            .padding(1.dp)
                            .weight(1f))
                        Column{
                            Text(
                                "Sunrise ${homeScreenState.sunrise}",
                                color = textColor,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                "Sunset ${homeScreenState.sunset}",
                                color = textColor,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
                else
                    ActionBar(location = homeScreenState.location, isOnline = true)
            }

        }


        Column(
            Modifier
                .padding(12.dp)
                .weight(0.7F)
                .verticalScroll(scrollState)
        ) {
            Time()
            Spacer(modifier=Modifier.height(16.dp))
            Row( modifier = modifier.fillMaxWidth()) {
                DetailsCard(modifier=Modifier.fillMaxWidth(0.45f),state = homeScreenState.wind, title = "Wind")
                Spacer(modifier.width(16.dp))
                DetailsCard(modifier=Modifier.fillMaxWidth(),state = homeScreenState.pressure, title = "Pressure")
            }
            Spacer(modifier.height(8.dp))
            Row (
                modifier = modifier.fillMaxWidth() ){
                DetailsCard(modifier=Modifier.fillMaxWidth(0.45f),state = homeScreenState.temperature, title = "Temp")
                Spacer(modifier.width(16.dp))
                DetailsCard(modifier=Modifier.fillMaxWidth(),state = homeScreenState.uv, title = "UV Index")
            }
            Spacer(modifier=Modifier.height(16.dp))
            HourlyForeCast()
            Spacer(modifier=Modifier.height(16.dp))
            AirQuality(modifier=Modifier.background(primaryLight),airQualityUi = homeScreenState.air)
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
        HomeScreen(homeScreenState = state)
    }

}

