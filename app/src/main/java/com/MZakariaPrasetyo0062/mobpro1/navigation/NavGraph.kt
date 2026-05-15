package com.MZakariaPrasetyo0062.mobpro1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.MZakariaPrasetyo0062.mobpro1.ui.screen.AboutScreen
import com.MZakariaPrasetyo0062.mobpro1.ui.screen.DetailScreen
import com.MZakariaPrasetyo0062.mobpro1.ui.screen.ListPlanScreen
import com.MZakariaPrasetyo0062.mobpro1.ui.screen.MainScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                navController = navController,
                onNavigateToAbout = { navController.navigate("about") }
            )
        }

        composable("list_plan") {
            ListPlanScreen(navController)
        }

        composable("about") {
            AboutScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(
            route = "detail?id={id}",
            arguments = listOf(navArgument("id") {
                type = NavType.LongType
                defaultValue = -1L
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")
            DetailScreen(
                navController = navController,
                id = if (id == -1L) null else id
            )
        }
    }
}