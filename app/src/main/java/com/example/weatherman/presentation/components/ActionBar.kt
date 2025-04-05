package com.example.weatherman.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherman.ui.theme.WeatherManTheme

@Composable
fun ActionBar(
    modifier: Modifier = Modifier,
    location: String,
    isOnline: Boolean,
    onAction:(OnAction)->Unit,
) {
    val focusManager = LocalFocusManager.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier.weight(0.5f)

        ) {
            Icon(
                Icons.Default.AccountCircle,null,
                modifier = Modifier.border(
                    width = 2.dp,
                    color = if(isOnline) Color.Green else Color.Transparent,
                    shape = CircleShape
                ).size(42.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .weight(2f)
        )  {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
            )
            BasicTextField(
                value = location,
                onValueChange = { onAction(OnAction.OnSearch(it))},
                modifier = Modifier
                    .fillMaxWidth()
                    .size(32.dp),
                textStyle = TextStyle(fontSize = 16.sp,textAlign = TextAlign.Center),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    focusManager.clearFocus()
                    onAction(OnAction.MakeSearch(location)) })
                )
        }
        IconButton(
            onClick = {},
            colors = IconButtonDefaults.iconButtonColors().copy(contentColor = Color.Black),
            modifier = Modifier.weight(0.5f)
        ) {
            Icon(
                Icons.Default.Menu,null,
                modifier = Modifier.fillMaxSize())
        }

    }
    
}

@Preview
@Composable
private fun PreviewActionBar() {
    WeatherManTheme {
        ActionBar(
            location = "Kampala,Uganda", isOnline = false,
            onAction = {}
        )
    }
    
}