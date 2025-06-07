package com.example.weatherman.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

        path.lineTo(250f,500f)

        path.lineTo(70f,500f)
        path.lineTo(200f,900f)

        path.close()




        // Draw the path
        drawPath(
            path = path,
            color = Color.Blue,
            style = Fill,
        )
    }
}
data class DataPoints(
    val xLabel:String,
    val yLabel:String,
    val yLabelValue:Int
)
//
//@Preview(device = Devices.PIXEL_7_PRO)
//@Composable
//private fun PreviewTrial() {
//    val path= mutableListOf<DataPoints>()
//    for(i in 0..6){
//        path.add(
//            DataPoints(
//            xLabel = "$i",
//            yLabel = "${i * 5}",
//            yLabelValue = i*5)
//        )
//    }
//    WeatherManTheme {
//        Trial(path=path)
//    }
//}


///////////////////////////////////////////////////////////////////////////


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun GlazedSurface() {
    val gradient = Brush.linearGradient(
           0f to  Color.Transparent,
           0.3f to  MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
           1f to MaterialTheme.colorScheme.primary
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .padding(8.dp)
                //.clip(RoundedCornerShape(16))
                .graphicsLayer(
                renderEffect =
                    android.graphics.RenderEffect.createBlurEffect(10f,10f,android.graphics.Shader.TileMode.MIRROR).asComposeRenderEffect()
            )

            ,
            tonalElevation =  8.dp
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize(),
                    onDraw = {
                        drawRect(Color.Blue.copy(alpha = 0.4f))
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview
@Composable
private fun PreviewGlass() {
    WeatherManTheme {
        GlazedSurface()
    }

}



//.graphicsLayer(
//renderEffect =
//android.graphics.RenderEffect.createBlurEffect(25f,25f,android.graphics.Shader.TileMode.MIRROR).asComposeRenderEffect()
//)







































