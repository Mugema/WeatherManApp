package com.example.weatherman.presentation.homeScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherman.presentation.mapper.toAirQualityUi
import com.example.weatherman.presentation.models.AirQualityUi
import com.example.weatherman.ui.theme.WeatherManTheme

@Composable
fun AirQuality(modifier: Modifier = Modifier,airQualityUi: AirQualityUi) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .shadow(4.dp, RoundedCornerShape(10), true)
            .clip(RoundedCornerShape(10))
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text("Air Quality",
            fontSize = 24.sp)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier=Modifier.padding(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .padding(8.dp)
                    .size(120.dp)
                    .border(
                        border = BorderStroke(
                            16.dp,
                            Brush.sweepGradient(
                                0.0f to Color.Blue,
                                0f to Color.Red,
                                0.75f to Color.Green.copy(green = 0.3f),
                                1f to Color.Yellow,
                            )
                        ),
                        shape = CircleShape
                    )

            )
            Column(
                modifier=Modifier.padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Box(modifier
                        .clip(CircleShape)
                        .size(18.dp)
                        .drawWithContent { drawRect(Color.Blue) })
                    Text(" Ozone")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Box(modifier
                        .clip(CircleShape)
                        .size(18.dp)
                        .drawWithContent { drawRect(Color.Red) })
                    Text(" Nitrogen dioxide")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Box(modifier
                        .clip(CircleShape)
                        .size(18.dp)
                        .drawWithContent { drawRect(Color.Yellow) })
                    Text(" Sulphur dioxide")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Box(
                        modifier
                            .clip(CircleShape)
                            .size(18.dp)
                            .drawWithContent { drawRect(Color.Green.copy(green = 0.3f)) }
                    )
                    Text(" Carbon dioxide")
                }
            }
        }

    }
}

@Preview
@Composable
private fun trial() {
    Box(
        modifier = Modifier.size(140.dp)
            .background(SolidColor(Color.Red))
    )

}

val air = com.example.weatherman.domain.models.AirQuality(
    carbonMonoxide = 10.0,
    nitrogenDioxide = 25.0,
    ozone = 50.0,
    sulphurDioxide = 15.0
).toAirQualityUi()

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun AirQualityPreview() {
    WeatherManTheme {
        AirQuality(airQualityUi = air)
    }


    
}