package com.example.weatherman.presentation.homeScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherman.R
import com.example.weatherman.presentation.homeScreen.HomeScreenViewModel
import com.example.weatherman.ui.theme.WeatherManTheme

@Composable
fun ForeCastRoot(modifier: Modifier = Modifier,viewmodel:HomeScreenViewModel) {

    
}
data class fore(
    val time:Int,
    val icon:Int,
    val temp:Int
)

val dd = fore(10,R.drawable.sun_1_,13)

@Composable
fun ForeCast(modifier: Modifier = Modifier,state: fore) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(start = 4.dp, end = 4.dp)
            .height(200.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(state.time.toString(), fontSize = 32.sp)
            Text("PM")
        }

        Icon(painter = painterResource(state.icon),
            contentDescription = null,
            modifier = Modifier.width(62.dp),
            tint = Color.Yellow)
        Text("${state.temp}°C", fontSize = 32.sp)
    }


}

@Preview
@Composable
private fun ForeCastPreview() {
    WeatherManTheme {
        ForeCast(state = dd)
    }

}