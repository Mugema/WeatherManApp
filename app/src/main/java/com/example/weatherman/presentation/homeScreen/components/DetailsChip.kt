package com.example.weatherman.presentation.homeScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.example.weatherman.presentation.homeScreen.HomeScreenViewModel
import com.example.weatherman.presentation.models.Weather
import com.example.weatherman.presentation.models.Wind
import com.example.weatherman.ui.theme.WeatherManTheme

@Composable
fun DetailsCardRoot(modifier: Modifier = Modifier,viewmodel:HomeScreenViewModel) {

}

@Composable
fun DetailsCard(modifier: Modifier = Modifier, state:Weather, title:String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier=modifier
            .shadow(4.dp, RoundedCornerShape(10), true)
            .clip(RoundedCornerShape(10))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Image(painter = painterResource(state.icon),
            contentDescription = null,
            modifier=Modifier
                .shadow(4.dp, CircleShape, true)
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(4.dp)
        )
        Column {
            Text(title, fontSize = 24.sp)
            Text(state.attrValue, fontSize = 16.sp)
        }
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.End,
//            modifier = Modifier.align(Alignment.Bottom)
//                //.fillMaxWidth()
//        ){
//            Icon(if(state.change==0) Icons.Default.KeyboardArrowLeft
//                else if (state.change<0) Icons.Default.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
//                contentDescription = null,
//                tint = if(state.change==0) Color.Gray
//                else if (state.change<0) Color.Red else Color.Green,
//                modifier = Modifier.size(32.dp))
//            Text("${abs(state.change)}", overflow = TextOverflow.Ellipsis)
//        }
    }

}
//
//@Composable
//fun Weathera(modifier: Modifier = Modifier) {
//    Column(
//        modifier.fillMaxSize()
//            .background(primaryColor)
//    ) {
//        Text("Conditions", textAlign = TextAlign.Center,
//            fontSize = 20.sp,
//            modifier = Modifier.fillMaxWidth())
//        Row {
//            Text("Wind")
//        }
//
//    }
//
//}
//
//@Preview
//@Composable
//private fun PreviewWeather() {
//    WeatherManTheme {
//        Weathera()
//    }
//
//}


@Preview
@Composable
private fun DetailCardPreview() {
    WeatherManTheme {
        DetailsCard(
            state = Wind(attrValue = "45.0 Kph", change = -10),
            title = "Pressure"
        )
    }

}