package com.example.caloriestracker.navigation

import androidx.navigation.NavController
import com.example.core.util.UiEvent

fun NavController.navigateToScreen(event: UiEvent.Navigate){
    this.navigate(event.route)
}