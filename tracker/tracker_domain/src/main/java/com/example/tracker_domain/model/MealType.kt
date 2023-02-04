package com.example.tracker_domain.model

enum class MealType {
    BREAKFAST,
    LUNCH,
    DINNER,
    SNACK
}

fun String.toMealType() =
    when {
        this == "breakfast" -> MealType.BREAKFAST
        this == "lunch" -> MealType.LUNCH
        this == "dinner" -> MealType.DINNER
        this == "snack" -> MealType.SNACK
        else -> MealType.BREAKFAST
    }
