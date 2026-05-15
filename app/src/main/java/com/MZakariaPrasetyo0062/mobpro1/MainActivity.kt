package com.MZakariaPrasetyo0062.mobpro1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.MZakariaPrasetyo0062.mobpro1.navigation.NavGraph
import com.MZakariaPrasetyo0062.mobpro1.ui.theme.Mobpro1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mobpro1Theme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}