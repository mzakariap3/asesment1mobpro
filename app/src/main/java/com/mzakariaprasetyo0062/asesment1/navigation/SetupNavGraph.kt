package com.mzakariaprasetyo0062.asesment1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mzakariaprasetyo0062.asesment1.ui.screen.MainScreen
import com.mzakariaprasetyo0062.asesment1.ui.screen.DetailScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ) {
        composable(route = "main_screen") {
            MainScreen(
                onNavigateToAbout = {
                    navController.navigate("about_screen")
                }
            )
        }
    }
}