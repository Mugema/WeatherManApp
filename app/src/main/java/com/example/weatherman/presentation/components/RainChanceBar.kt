package com.example.weatherman.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherman.ui.theme.WeatherManTheme
import com.example.weatherman.ui.theme.primaryLight

@Composable
fun RainChance(modifier: Modifier = Modifier,time:String,percentage:Int) {
    val coloredWeight=(percentage/100F)
    val whiteWeight = 1-coloredWeight
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(4.dp)
    ) {
        Spacer(modifier = Modifier.width(4.dp))
        Text(time)
        Spacer(modifier.width(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){
            Box(modifier=Modifier
                .weight(coloredWeight)
                .background(primaryLight, shape = RoundedCornerShape(32.dp))
                .fillMaxWidth()
                .height(20.dp)
            )
            Box(modifier=Modifier
                .weight(whiteWeight)
                .background(Color.White,shape = RoundedCornerShape(32.dp))
                .fillMaxWidth()
                .height(20.dp)
            )
            Spacer(modifier.width(8.dp))
            Text("$percentage%")
            Spacer(modifier = Modifier.width(4.dp))
        }




    }

}

@Preview
@Composable
private fun RainChancePreview() {
    WeatherManTheme {
        RainChance(time = "7pm", percentage = 70)
    }
    
}