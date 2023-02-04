package com.example.tracker_data.mapper

import com.example.tracker_data.local.entity.TrackedFoodEntity
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.model.toMealType
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood(): TrackedFood{
    return TrackedFood(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        mealType = type.toMealType(),
        amount = amount,
        date = LocalDate.of(year, month, dayOfMonth),
        calories = calories,
        id = id
    )
}
fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        type = mealType.name,
        amount = amount,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        calories = calories,
        id = id
    )
}