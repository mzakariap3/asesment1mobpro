package com.mzakariaprasetyo0062.asesment1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mzakariaprasetyo0062.asesment1.ui.screen.MainScreen
import com.mzakariaprasetyo0062.asesment1.ui.screen.AboutScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                onNavigateToAbout = { navController.navigate("about") }
            )
        }
        composable("about") {
            AboutScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}