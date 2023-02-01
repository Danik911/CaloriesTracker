package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.caloriestracker.navigation.SetupNavigation
import com.example.caloriestracker.ui.theme.CaloriesTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloriesTrackerTheme {
                navHostController = rememberNavController()
                SetupNavigation(navController = navHostController)
            }
        }
    }
}
