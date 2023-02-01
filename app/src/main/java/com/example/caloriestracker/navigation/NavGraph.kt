package com.example.caloriestracker.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core.navigation.Route
import com.example.onboarding_presentation.age_screen.AgeScreen
import com.example.onboarding_presentation.gender_screen.GenderScreen
import com.example.onboarding_presentation.welcome_screen.WelcomeScreen

@Composable
fun SetupNavigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = Route.WELCOME
    ) {
        composable(Route.WELCOME) {
            WelcomeScreen(onNavigate = navController::navigateToScreen)
        }
        composable(Route.AGE) {
            AgeScreen(
                scaffoldState = scaffoldState,
                onNavigate = navController::navigateToScreen
            )
        }
        composable(Route.GENDER) {
            GenderScreen(onNavigate = navController::navigateToScreen)
        }
        composable(Route.HEIGHT) {

        }
        composable(Route.NUTRIENT_GOAL) {

        }
        composable(Route.ACTIVITY) {

        }
        composable(Route.GOAL) {

        }
        composable(Route.TRACKER_OVERVIEW) {

        }
        composable(Route.SEARCH) {

        }
    }
}