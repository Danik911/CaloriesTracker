package com.example.core.domain.preferences

import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface Preferences {

    suspend fun saveGender(gender: Gender)
    suspend fun saveAge(age: Int)
    suspend fun saveWeight(weight: Float)
    suspend fun saveHeight(height: Int)
    suspend fun saveActivityLevel(activityLevel: ActivityLevel)
    suspend fun saveGoalType(goalType: GoalType)
    suspend fun saveCarbRatio(carbRatio: Float)
    suspend fun saveProteinRatio(proteinRatio: Float)
    suspend fun saveFatRatio(fatRatio: Float)


    fun loadUserInfo(): UserInfo

    suspend fun saveShouldShowOnboarding(showOnboarding: Boolean)
    fun loadShouldShowOnboarding(): Boolean

    companion object {
        const val KEY_GENDER = "gender"
        const val KEY_AGE = "age"
        const val KEY_WEIGHT = "weight"
        const val KEY_HEIGHT = "height"
        const val KEY_ACTIVITY_LEVEL = "activity_level"
        const val KEY_GOAL_TYPE = "goal_type"
        const val KEY_CARB_RATIO = "carb_ratio"
        const val KEY_PROTEIN_RATIO = "protein_ratio"
        const val KEY_FAT_RATIO = "fat_ratio"
        const val KEY_SHOULD_SHOW_ONBOARDING = "should_show_onboarding"
    }

}

