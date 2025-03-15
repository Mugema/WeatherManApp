package com.example.weatherman.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherman.R
import com.example.weatherman.ui.theme.WeatherManTheme
import com.example.weatherman.ui.theme.primaryColor

@Composable
fun <T> TimeRoot (modifier: Modifier = Modifier,model:T) {

}

@Composable
fun Time(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.shadow(4.dp, RoundedCornerShape(40),true,Color.Black)
                .clip(RoundedCornerShape(40))
                .background(primaryColor)
                .padding(start = 8.dp, end = 8.dp)
        ) {
            Text(stringResource(R.string.today),
            fontSize = 16.sp,
            modifier = Modifier.padding(12.dp))  }
        Spacer(modifier = Modifier.width(8.dp))

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(40),true,Color.Black)
                .clip(RoundedCornerShape(40))
                .background(Color.White)
                .padding(start = 8.dp, end = 8.dp)

        ) { Text(stringResource(R.string.tomorrow),
            fontSize = 16.sp,
            modifier = Modifier.padding(12.dp)) }
        Spacer(modifier = Modifier.width(8.dp))

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(40),true,Color.Black)
                .clip(RoundedCornerShape(40))
                .background(Color.White)
                .padding(start = 8.dp, end = 8.dp)
        ) { Text(stringResource(R.string.three_Days),
            fontSize = 16.sp,
            modifier = Modifier.padding(12.dp))  }
        Spacer(modifier = Modifier.width(8.dp))
    }

}

//0a5d02c04b8fb59c9ae14cf428f58e2c9c27cb7fc9098df1fb4290900c6e2e59bcd594dc6202e3a52e33831a22a238ae81cd5ec4aac5087ee38a3fc69d8ec3ef7f7346ce82fafbbd7327de8066988a506ee3dcd1f6686168e92e9a5f3c4e83cd3bb4c0a9bf5fedc68c5147178ca0c568
//761b62b9-9f95-4312-942a-76a7945d7c9b

@Preview
@Composable
private fun TimePreview() {
    WeatherManTheme {
        Time()
    }
}