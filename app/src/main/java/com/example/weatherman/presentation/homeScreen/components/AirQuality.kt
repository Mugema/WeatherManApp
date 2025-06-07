package com.example.weatherman.presentation.homeScreen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextOverflow
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
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(10), true)
            .clip(RoundedCornerShape(10))
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Text("Air Quality",
            fontSize = 24.sp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            DrawChart(
                modifier = Modifier.size(200.dp),
                carbondioxideSize = airQualityUi.carbonMonoxide,
                ozoneSize = airQualityUi.ozone,
                sulphurdioxide = airQualityUi.sulphurDioxide,
                nitrogendioxide = airQualityUi.nitrogenDioxide
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
                        .drawWithContent { drawRect(Color.Blue) }
                    )
                    Spacer(modifier=Modifier.width(4.dp))
                    Text("Ozone", overflow = TextOverflow.Visible, maxLines = 1)
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Box(modifier
                        .clip(CircleShape)
                        .size(18.dp)
                        .drawWithContent { drawRect(Color.Red) })
                    Spacer(modifier=Modifier.width(4.dp))
                    Text("Nitrogen dioxide",overflow = TextOverflow.Visible, maxLines = 1)
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Box(modifier
                        .clip(CircleShape)
                        .size(18.dp)
                        .drawWithContent { drawRect(Color.Yellow) })
                    Spacer(modifier=Modifier.width(4.dp))
                    Text("Sulphur dioxide",overflow = TextOverflow.Visible, maxLines = 1)
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
                    Spacer(modifier=Modifier.width(4.dp))
                    Text("Carbon dioxide",overflow = TextOverflow.Visible, maxLines = 1)
                }
            }
        }
    }
}
@Composable
private fun DrawChart(
    modifier: Modifier = Modifier,
    ozoneSize:Float = 0f,
    nitrogendioxide:Float = 0f,
    sulphurdioxide: Float = 0f,
    carbondioxideSize: Float = 0f
){
    val sulphur = 360*sulphurdioxide
    val ozone = 360*ozoneSize
    val nitrogen = 360*nitrogendioxide
    val carbondioxide = 360*carbondioxideSize

    Canvas(modifier = modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(8.dp)
    ) {
        drawArc(brush = SolidColor(Color.Yellow), startAngle = 0f, sweepAngle = sulphur,true)
        drawArc(brush = SolidColor(Color.Red), startAngle = sulphur, sweepAngle = nitrogen,true)
        drawArc(brush = SolidColor(Color.Blue), startAngle = nitrogen+sulphur, sweepAngle = ozone,true)
        drawArc(brush = SolidColor(Color.Green), startAngle = ozone+nitrogen+sulphur, sweepAngle = carbondioxide,true)
        drawCircle(brush = SolidColor(Color.White), radius = size.width/3f)
    }
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

@Preview
@Composable
private fun PreviewDrawChart() {
    WeatherManTheme {
        Column {
            DrawChart(
                modifier = Modifier.size(300.dp),
                ozoneSize = 0.71f,
                carbondioxideSize = 0.12f,
                nitrogendioxide = 0.11f,
                sulphurdioxide = 0.06f

            )
        }
    }
}
