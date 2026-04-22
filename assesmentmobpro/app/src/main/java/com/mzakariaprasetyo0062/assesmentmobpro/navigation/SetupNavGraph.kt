package com.mzakariaprasetyo0062.assesmentmobpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mzakariaprasetyo0062.assesmentmobpro.ui.screen.AboutScreen
import com.mzakariaprasetyo0062.assesmentmobpro.ui.screen.MainScreen

@Composable
fun SetupNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ) {
        composable(route = "main_screen") {
            MainScreen(onNavigateToAbout = { navController.navigate("about_screen") })
        }
        composable(route = "about_screen") {
            AboutScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}