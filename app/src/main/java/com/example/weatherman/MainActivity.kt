package com.example.weatherman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.weatherman.navigation.Navigation
import com.example.weatherman.ui.theme.WeatherManTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity () : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModels<MainActivityViewModel>()
            val loading = viewModel.isLoading.collectAsStateWithLifecycle().value
            WeatherManTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets.safeGestures
                ) { innerPadding ->
                    if(loading){
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.background(Color.White).fillMaxSize()){
                            Image(painter = painterResource(R.drawable.weather),
                                contentDescription = null)
                            LinearProgressIndicator()
                        }
                    }
                    else{
                        val controller = rememberNavController()
                        Navigation(navController = controller)
                    }

                }
            }
        }
    }
}
