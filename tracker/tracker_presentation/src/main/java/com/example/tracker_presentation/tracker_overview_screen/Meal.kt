package com.example.tracker_presentation.tracker_overview_screen

import androidx.annotation.DrawableRes
import com.example.core.R.drawable
import com.example.core.R.string
import com.example.core.util.UiText
import com.example.tracker_domain.model.MealType

data class Meal(
    val name: UiText,
    @DrawableRes val drawableRes: Int,
    val mealType: MealType,
    val carbs: Int = 0,
    val protein: Int = 0,
    val fat: Int = 0,
    val calories: Int = 0,
    val isExpanded: Boolean = false
)

val defaultMeals = listOf(
    Meal(
        name = UiText.StringResource(string.breakfast),
        drawableRes = drawable.ic_breakfast,
        mealType = MealType.BREAKFAST
    ),
    Meal(
        name = UiText.StringResource(string.lunch),
        drawableRes = drawable.ic_lunch,
        mealType = MealType.LUNCH
    ),
    Meal(
        name = UiText.StringResource(string.dinner),
        drawableRes = drawable.ic_dinner,
        mealType = MealType.DINNER
    ),

)
