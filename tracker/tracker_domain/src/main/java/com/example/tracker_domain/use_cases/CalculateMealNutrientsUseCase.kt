package com.example.tracker_domain.use_cases

import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.preferences.Preferences
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlin.math.roundToInt

class CalculateMealNutrientsUseCase(private val preferences: Preferences) {

    operator fun invoke(trackedFoods: List<TrackedFood>): Flow<Result> = flow {

        val allNutrients = trackedFoods
            .groupBy { it.mealType }
            .mapValues { entry ->
                val type = entry.key
                val foods = entry.value
                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    protein = foods.sumOf { it.protein },
                    fat = foods.sumOf { it.fat },
                    calories = foods.sumOf { it.calories },
                    mealType = type
                )
            }
        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalProtein = allNutrients.values.sumOf { it.protein }
        val totalFat = allNutrients.values.sumOf { it.fat }
        val totalCalories = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()
        /*val caloryGoal = dailyCaloryRequirement(userInfo)
        val carbsGoal = (caloryGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (caloryGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (caloryGoal * userInfo.fatRatio / 9f).roundToInt()*/

        userInfo.map { userInfoFlow->
            val caloryGoal = dailyCaloryRequirement(userInfoFlow)
            val carbsGoal = (caloryGoal * userInfoFlow.carbRatio / 4f).roundToInt()
            val proteinGoal = (caloryGoal * userInfoFlow.proteinRatio / 4f).roundToInt()
            val fatGoal = (caloryGoal * userInfoFlow.fatRatio / 9f).roundToInt()

             val result = Result(
                carbsGoal = carbsGoal,
                proteinGoal = proteinGoal,
                fatGoal = fatGoal,
                caloriesGoal = caloryGoal,
                totalCarbs = totalCarbs,
                totalProtein = totalProtein,
                totalFat = totalFat,
                totalCalories = totalCalories,
                mealNutrients = allNutrients
            )
            emit(result)
        }

    }


    private fun bmr(userInfo: UserInfo): Int {
        return when (userInfo.gender) {
            Gender.MALE -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }
            Gender.FEMALE -> {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67 * userInfo.age).roundToInt()
            }
        }
    }

    private fun dailyCaloryRequirement(userInfo: UserInfo): Int {
        val activityFactor = when (userInfo.activityLevel) {
            ActivityLevel.LOW -> 1.2f
            ActivityLevel.MEDIUM -> 1.3f
            ActivityLevel.HIGH -> 1.4f
        }
        val caloryExtra = when (userInfo.goalType) {
            GoalType.LOSE_WEIGHT -> -500
            GoalType.KEEP_WEIGHT -> 0
            GoalType.GAIN_WEIGHT -> 500
        }
        return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
    }


    data class MealNutrients(
        val carbs: Int,
        val protein: Int,
        val calories: Int,
        val fat: Int,
        val mealType: MealType
    )

    data class Result(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val caloriesGoal: Int,
        val totalCarbs: Int,
        val totalProtein: Int,
        val totalFat: Int,
        val totalCalories: Int,
        val mealNutrients: Map<MealType, MealNutrients>
    )
}