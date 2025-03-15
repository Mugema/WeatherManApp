package com.example.weatherman.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherman.ui.theme.WeatherManTheme

@Composable
fun Trial() {
    // Create ScrollState to own it and be able to control scroll behaviour of scrollable Row below
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val path = Path()

    Canvas(modifier = Modifier.size(200.dp)
        .background(Color.White)) {
        val radius=this.size.width/2
        path.arcTo(
            Rect(
                center.x,
                center.y,
                center.x+this.size.width/2,
                center.y+this.size.width/2
            ),
            startAngleDegrees = 270f,
            sweepAngleDegrees = -180f,
            forceMoveTo = false
        )
        path.arcTo(
            Rect(
                center.x - radius / 2,
                center.y,
                center.x + radius / 2,
                center.y + radius
            ),
            270f,
            -180f,
            false
        )

        drawPath(path,Color.Black)

    }





//    Canvas(modifier = Modifier.size(150.dp)
//        .background(Color.White)) {
//        val radius = this.size.minDimension/2
//        path.arcTo(
//            Rect(0f, 0f,center.x+radius,center.y+radius),
//            startAngleDegrees = 90f,
//            sweepAngleDegrees = 180f,
//            forceMoveTo = false
//        )
//        path.arcTo(
//            Rect(center.x-radius /2, 0f,center.x+radius /2,center.y),
//            startAngleDegrees = 270f,
//            sweepAngleDegrees = 180f,
//            forceMoveTo = false
//
//        )
//
//        path.arcTo(
//            Rect(
//                center.x - radius / 2,
//                center.y,
//                center.x + radius / 2,
//                center.y + radius
//            ),
//            270f,
//            -180f,
//            false
//        )
//
//        drawPath(path,Color.Black)
//    }



}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun PreviewTrial() {
    WeatherManTheme {
        Trial()
    }
    
}