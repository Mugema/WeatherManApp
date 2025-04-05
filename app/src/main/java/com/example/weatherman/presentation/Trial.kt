package com.example.weatherman.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import com.example.weatherman.R
import com.example.weatherman.ui.theme.WeatherManTheme

@Composable
fun Trial(path:List<DataPoints>) {
    // Create ScrollState to own it and be able to control scroll behaviour of scrollable Row below
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val textMeasure = rememberTextMeasurer()

//    Canvas(modifier = Modifier
//        .size(200.dp)
//        .background(Color.White)
//    ) {
//        drawRect(color = Color.LightGray,
//            topLeft = Offset(100f,100f),
//            size = Size(500f,500f)
//        )
//        val measured = path.map { textMeasure.measure(it.xLabel, style = TextStyle.Default.copy(fontSize = 10.sp)) }
//
//
//        measured.forEachIndexed { index, textLayoutResult ->
//            val i = index+1
//            drawText(
//                textLayoutResult,
//                topLeft = Offset(100f*i,600f))
//        }
//
//        val yMeasured =  path.map { textMeasure.measure(it.yLabel,style = TextStyle.Default.copy(fontSize = 10.sp)) }
//
//        yMeasured.forEachIndexed { index, textLayoutResult ->
//            val i = index+1
//            drawText(
//                textLayoutResult,
//                topLeft = Offset(
//                    100f-textLayoutResult.size.width,
//                    100f*i
//                )
//            )
//        }
//
//        val ee = Path();
//        path.forEach {
//            ee.lineTo(it.xLabel.toFloat(),it.yLabelValue.toFloat())
//        }
//        ee.close()
//        drawPath(ee,Color.Black)
//
//    }

    Canvas(
        modifier = Modifier
            .size(500.dp)
            .background(Color.White)
    ) {
        val path = Path()

        // Move to the starting point
        path.moveTo(450f, size.height-100f)

        // Draw a line to another point
        path.lineTo(700f, 50f)

        // Draw a line to another point
        path.lineTo(200f, 200f)




        // Draw the path
        drawPath(
            path = path,
            color = Color.Blue,
            style = Stroke(width = 5.dp.toPx(),)
        )
    }
}
data class DataPoints(
    val xLabel:String,
    val yLabel:String,
    val yLabelValue:Int
)

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun PreviewTrial() {
    val path= mutableListOf<DataPoints>()
    for(i in 0..6){
        path.add(
            DataPoints(
            xLabel = "$i",
            yLabel = "${i * 5}",
            yLabelValue = i*5)
        )
    }
    WeatherManTheme {
        Trial(path=path)
    }
}
