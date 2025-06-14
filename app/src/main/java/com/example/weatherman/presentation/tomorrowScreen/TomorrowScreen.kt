package com.example.weatherman.presentation.tomorrowScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherman.presentation.components.Rain
import com.example.weatherman.presentation.components.TopBar
import com.example.weatherman.presentation.tomorrowScreen.components.AstroDetails
import com.example.weatherman.presentation.tomorrowScreen.components.ExpectCard
import com.example.weatherman.presentation.tomorrowScreen.components.ss
import com.example.weatherman.presentation.tomorrowScreen.components.sunSet1
import com.example.weatherman.ui.theme.WeatherManTheme

@Composable
fun TomorrowScreenRoot(modifier: Modifier = Modifier,viewModel: TomorrowViewModel) {
    
}

@Composable
fun TomorrowScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
    ) {
        TopBar(location = "Kampala,Uganda", isOnline = true)

        Column(
            modifier = Modifier.verticalScroll(scrollState)
                .fillMaxSize()
        ){
            ExpectCard(modifier = Modifier.padding(8.dp),expectedState = ss)
            Rain(modifier = Modifier.padding(8.dp))
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier=Modifier.padding(4.dp).fillMaxWidth()
        ){
            AstroDetails(sunSet1,modifier = Modifier.weight(1f).padding(8.dp))
            AstroDetails(sunSet1,modifier = Modifier.weight(1f).padding(8.dp))
        }

    }
    
}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun TomorrowScreenPreview() {
    WeatherManTheme {
        TomorrowScreen()
    }

}