package com.example.weatherman.presentation.tomorrowScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExpectCard(modifier: Modifier = Modifier,expectedState:ExpectedState) {
    Column(
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(10),true)
            .height(150.dp)
            .padding(2.dp)
            .clip(RoundedCornerShape(10))
            .background(Color.White)
            .fillMaxWidth()


    ) {
        Text(
            "What to expect",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp, bottom = 8.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row {
            Box(modifier = Modifier.size(64.dp)){
                CircularProgressIndicator(modifier = modifier.fillMaxSize())
            }
            Spacer(modifier=Modifier.width(32.dp))

            Column(verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier) {
                Text("Max Temp: ${expectedState.maxTemp}°C",)
                Text("Min Temperature: ${expectedState.minTemp}°C")
                Text("Max Wind Speed: ${expectedState.minTemp}Km/h")
                Text(expectedState.dayCondition)
            }
        }
        Spacer(modifier.height(8.dp))
    }

}
data class ExpectedState(
    val maxTemp:String,
    val minTemp:String,
    val dayCondition:String
)

val ss = ExpectedState("32","17","Cloudy")
@Preview(device = Devices.PIXEL_6_PRO)
@Composable
private fun ExpectCardPreview() {
    ExpectCard(expectedState = ss)
    
}