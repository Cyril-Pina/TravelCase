package com.cyriltheandroid.travelcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsControllerCompat
import com.cyriltheandroid.travelcase.navigation.TravelCaseNavHost
import com.cyriltheandroid.travelcase.theme.TravelCaseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            TravelCaseTheme {
                TravelCaseNavHost(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
