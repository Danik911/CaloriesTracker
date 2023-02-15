package com.example.core.data.preferences

import android.content.SharedPreferences
import com.example.core.domain.model.*
import com.example.core.domain.preferences.Preferences


class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences {
    override suspend fun saveGender(gender: Gender) {
        TODO("Not yet implemented")
    }

    override suspend fun saveAge(age: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun saveWeight(weight: Float) {
        TODO("Not yet implemented")
    }

    override suspend fun saveHeight(height: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun saveActivityLevel(activityLevel: ActivityLevel) {
        TODO("Not yet implemented")
    }

    override suspend fun saveGoalType(goalType: GoalType) {
        TODO("Not yet implemented")
    }

    override suspend fun saveCarbRatio(carbRatio: Float) {
        TODO("Not yet implemented")
    }

    override suspend fun saveProteinRatio(proteinRatio: Float) {
        TODO("Not yet implemented")
    }

    override suspend fun saveFatRatio(fatRatio: Float) {
        TODO("Not yet implemented")
    }

    override fun loadUserInfo(): UserInfo {

        val age = sharedPref.getInt(Preferences.KEY_AGE, -1)
        val height = sharedPref.getInt(Preferences.KEY_HEIGHT, -1)
        val weight = sharedPref.getFloat(Preferences.KEY_WEIGHT, -1f)
        val genderString = sharedPref.getString(Preferences.KEY_GENDER, null)
        val activityLevelString = sharedPref
            .getString(Preferences.KEY_ACTIVITY_LEVEL, null)
        val goalType = sharedPref.getString(Preferences.KEY_GOAL_TYPE, null)
        val carbRatio = sharedPref.getFloat(Preferences.KEY_CARB_RATIO, -1f)
        val proteinRatio = sharedPref.getFloat(Preferences.KEY_PROTEIN_RATIO, -1f)
        val fatRatio = sharedPref.getFloat(Preferences.KEY_FAT_RATIO, -1f)

        return UserInfo(
            gender = genderString?.getGenderFromString() ?: Gender.MALE,
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevelString ?: "medium"),
            goalType = GoalType.fromString(goalType ?: "keep_weight"),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }

    override suspend fun saveShouldShowOnboarding(showOnboarding: Boolean) {
        TODO("Not yet implemented")
    }

    override fun loadShouldShowOnboarding(): Boolean {

    }
}