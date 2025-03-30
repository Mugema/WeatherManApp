package com.example.weatherman.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherman.presentation.homeScreen.HomeScreenRoot
import com.example.weatherman.presentation.tenDayScreen.ForeCastScreenRoot
import com.example.weatherman.presentation.tomorrowScreen.TomorrowScreenRoot


@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    //val selected by rememberSaveable { mutableStateOf(Selected()) }

    NavHost(navController=navController, startDestination = Routes.Home) {
        composable<Routes.Home> {
            HomeScreenRoot(
                navigateToTomorrowScreen = { navController.navigate(Routes.Tomorrow) },
                navigateToForecastScreen = { navController.navigate(Routes.Forecast) }
            )
        }
        composable<Routes.Tomorrow> {
            TomorrowScreenRoot(
                toHomeScreen = { navController.navigate(Routes.Home) },
                toForeCastScreen = { navController.navigate(Routes.Forecast) }
            )
        }
        composable<Routes.Forecast> {
            ForeCastScreenRoot(
                navigateToTomorrowScreen = { navController.navigate(Routes.Tomorrow) },
                navigateToHomeScreen = { navController.navigate(Routes.Forecast) }
            )

        }
    }

}