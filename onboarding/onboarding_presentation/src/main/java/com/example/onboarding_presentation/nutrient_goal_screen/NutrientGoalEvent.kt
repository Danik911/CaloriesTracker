package com.example.onboarding_presentation.nutrient_goal_screen

sealed class NutrientGoalEvent{
    data class OnCarbsRatioEnter(val carbsRatio: String): NutrientGoalEvent()
    data class OnProteinRatioEnter(val proteinRatio: String): NutrientGoalEvent()
    data class OnFatRatioEnter(val fatRatio: String): NutrientGoalEvent()
    object OnNextClick: NutrientGoalEvent()
}
