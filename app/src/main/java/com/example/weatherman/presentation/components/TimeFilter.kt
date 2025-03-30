package com.example.weatherman.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun Time(
    modifier: Modifier = Modifier,
    onTodayClicked:()->Unit={},
    onTomorrowClicked:() ->Unit={},
    onForeCastClicked:()->Unit={},
    selected: SelectedScreen
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .width(IntrinsicSize.Max)
            .fillMaxWidth()
            .padding(top = 8.dp),
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(40))
                .clip(RoundedCornerShape(40))
                .background(if (selected.todayScreen) primaryColor else Color.White)
                .clickable { onTodayClicked() }
                .padding(start = 8.dp, end = 8.dp)
        ) {
            Text(stringResource(R.string.today),
            fontSize = 16.sp,
            modifier = Modifier.padding(12.dp))
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(40),true,Color.Black)
                .clip(RoundedCornerShape(40))
                .background(if (selected.tomorrowScreen) primaryColor else Color.White)
                .clickable { onTomorrowClicked() }
                .padding(start = 8.dp, end = 8.dp)
        ) { Text(stringResource(R.string.tomorrow),
            fontSize = 16.sp,
            modifier = Modifier.padding(12.dp))
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(40),true,Color.Black)
                .clip(RoundedCornerShape(40))
                .background(if (selected.foreCastScreen) primaryColor else Color.White)
                .clickable { onForeCastClicked() }
                .padding(start = 8.dp, end = 8.dp)
        ) { Text(stringResource(R.string.three_Days),
            fontSize = 16.sp,
            modifier = Modifier.padding(12.dp))
        }
    }
}

@Preview
@Composable
private fun TimePreview() {
    WeatherManTheme {
        Time(selected = SelectedScreen(false,false,true))
    }
}

data class SelectedScreen(
    val todayScreen:Boolean = true,
    val tomorrowScreen: Boolean = false,
    val foreCastScreen: Boolean = false
)