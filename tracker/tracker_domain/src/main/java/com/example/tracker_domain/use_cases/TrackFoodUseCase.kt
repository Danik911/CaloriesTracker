package com.example.tracker_domain.use_cases

import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFoodUseCase(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate
    ) {
        repository.insertTrackedFood(
            TrackedFood(
                name = food.name,
                carbs = food.carbsPer100g.calculateAmount(amount),
                protein = food.proteinPer100g.calculateAmount(amount),
                fat = food.fatPer100g.calculateAmount(amount),
                calories = food.caloriesPer100g.calculateAmount(amount),
                imageUrl = food.imageUrl,
                mealType = mealType,
                amount = amount,
                date = date,
            )
        )
    }

    private fun Int.calculateAmount(amount: Int) = (this / 100f * amount).roundToInt()

}