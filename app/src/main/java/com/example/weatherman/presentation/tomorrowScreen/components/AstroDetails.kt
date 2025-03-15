package com.example.weatherman.presentation.tomorrowScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherman.R
import com.example.weatherman.presentation.models.AstroState
import com.example.weatherman.ui.theme.WeatherManTheme
import com.example.weatherman.ui.theme.primaryColor

@Composable
fun AstroDetails(
    astroState: AstroState,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .shadow(
                elevation = 30.dp,
                shape = RoundedCornerShape(10),
                clip = false,
                ambientColor = Color.Black,
                spotColor = Color.Yellow
            )
            .clip(RoundedCornerShape(10))
            .background(primaryColor)
            .padding(8.dp)
            .height(64.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(astroState.image),
                contentDescription = null,
                modifier = Modifier
                    .height(60.dp)
                    .padding(4.dp)
            )
        }
        Spacer(modifier=Modifier.width(4.dp))
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(astroState.event, fontSize = 20.sp )
            Text(astroState.time)
        }
    }


}

val sunSet1= AstroState(
    image = R.drawable.sun_1_,
    event = "Sunrise",
    time = "6:25 PM",
)
@Preview
@Composable
private fun AstroDetailsPreview() {
    WeatherManTheme {
        AstroDetails(sunSet1)
    }

}