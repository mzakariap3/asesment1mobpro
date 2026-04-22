package com.mzakariaprasetyo0062.assesmentmobpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mzakariaprasetyo0062.assesmentmobpro.navigation.SetupNavGraph
import com.mzakariaprasetyo0062.assesmentmobpro.ui.theme.AssesmentmobproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssesmentmobproTheme {
                SetupNavGraph()
            }
        }
    }
}