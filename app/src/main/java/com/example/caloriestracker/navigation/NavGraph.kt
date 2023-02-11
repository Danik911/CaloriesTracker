package com.example.caloriestracker.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.core.navigation.Route
import com.example.onboarding_presentation.activity_screen.ActivityScreen
import com.example.onboarding_presentation.age_screen.AgeScreen
import com.example.onboarding_presentation.gender_screen.GenderScreen
import com.example.onboarding_presentation.goal_screen.GoalScreen
import com.example.onboarding_presentation.height_screen.HeightScreen
import com.example.onboarding_presentation.height_screen.HeightViewModel
import com.example.onboarding_presentation.nutrient_goal_screen.NutrientGoalScreen
import com.example.onboarding_presentation.weight_screen.WeightScreen
import com.example.onboarding_presentation.welcome_screen.WelcomeScreen
import com.example.tracker_presentation.search_screen.SearchScreen
import com.example.tracker_presentation.tracker_overview_screen.TrackerOverviewScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SetupNavigation(
    navController: NavHostController, scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController, startDestination = Route.WELCOME
    ) {
        composable(Route.WELCOME) {
            WelcomeScreen(onNavigate = navController::navigateToScreen)
        }
        composable(Route.AGE) {
            AgeScreen(
                scaffoldState = scaffoldState, onNavigate = navController::navigateToScreen
            )
        }
        composable(Route.GENDER) {
            GenderScreen(onNavigate = navController::navigateToScreen)
        }
        composable(Route.WEIGHT) {
            WeightScreen(
                scaffoldState = scaffoldState, onNavigate = navController::navigateToScreen
            )
        }
        composable(Route.HEIGHT) {
            HeightScreen(
                scaffoldState = scaffoldState, onNavigate = navController::navigateToScreen
            )
        }
        composable(Route.NUTRIENT_GOAL) {
            NutrientGoalScreen(
                scaffoldState = scaffoldState, onNavigate = navController::navigateToScreen
            )

        }
        composable(Route.ACTIVITY) {
            ActivityScreen(
                onNavigate = navController::navigateToScreen
            )

        }
        composable(Route.GOAL) {
            GoalScreen(
                onNavigate = navController::navigateToScreen
            )
        }
        composable(Route.TRACKER_OVERVIEW) {
            TrackerOverviewScreen(
                onNavigate = navController::navigateToScreen
            )
        }
        composable(
            Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
            arguments = listOf(
                navArgument("mealName") {
                    type = NavType.StringType
                },
                navArgument("dayOfMonth") {
                    type = NavType.IntType
                },
                navArgument("month") {
                    type = NavType.IntType
                },
                navArgument("year") {
                    type = NavType.IntType
                },
            )
        ) {
            val mealName = it.arguments?.getString("mealName")!!
            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
            val month = it.arguments?.getInt("month")!!
            val year = it.arguments?.getInt("year")!!

            SearchScreen(
                scaffoldState = scaffoldState,
                mealName = mealName,
                dayOfMonth = dayOfMonth,
                month = month,
                year = year,
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}