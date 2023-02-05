package com.example.core.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.preferences.DataStorePreferences
import com.example.core.domain.preferences.DataStorePreferences.Companion.KEY_ACTIVITY_LEVEL
import com.example.core.domain.preferences.DataStorePreferences.Companion.KEY_AGE
import com.example.core.domain.preferences.DataStorePreferences.Companion.KEY_CARB_RATIO
import com.example.core.domain.preferences.DataStorePreferences.Companion.KEY_FAT_RATIO
import com.example.core.domain.preferences.DataStorePreferences.Companion.KEY_GENDER
import com.example.core.domain.preferences.DataStorePreferences.Companion.KEY_GOAL_TYPE
import com.example.core.domain.preferences.DataStorePreferences.Companion.KEY_HEIGHT
import com.example.core.domain.preferences.DataStorePreferences.Companion.KEY_PROTEIN_RATIO
import com.example.core.domain.preferences.DataStorePreferences.Companion.KEY_SHOULD_SHOW_ONBOARDING
import com.example.core.domain.preferences.DataStorePreferences.Companion.KEY_WEIGHT
import com.example.core.util.Constants.CALORIES_TRACKER_PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


val Context.dataStorePref: DataStore<Preferences> by preferencesDataStore(name = CALORIES_TRACKER_PREFERENCES_NAME)

class DataStorePreferencesImpl(context: Context) :
    DataStorePreferences {

    private val dataStore = context.dataStorePref

    private object PreferencesKey {
        val genderKey = stringPreferencesKey(name = KEY_GENDER)
        val ageKey = intPreferencesKey(name = KEY_AGE)
        val weightKey = floatPreferencesKey(name = KEY_WEIGHT)
        val heightKey = intPreferencesKey(name = KEY_HEIGHT)
        val activityLevelKey = stringPreferencesKey(name = KEY_ACTIVITY_LEVEL)
        val goalTypeKey = stringPreferencesKey(name = KEY_GOAL_TYPE)
        val carbRatioKey = floatPreferencesKey(name = KEY_CARB_RATIO)
        val proteinRationKey = floatPreferencesKey(name = KEY_PROTEIN_RATIO)
        val fatRatioKey = floatPreferencesKey(name = KEY_FAT_RATIO)
        val showOnboarding = booleanPreferencesKey(name = KEY_SHOULD_SHOW_ONBOARDING)

    }

    override suspend fun saveGender(gender: Gender) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.genderKey] = gender.name
        }
    }

    override suspend fun saveAge(age: Int) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.ageKey] = age
        }
    }

    override suspend fun saveWeight(weight: Float) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.weightKey] = weight
        }
    }

    override suspend fun saveHeight(height: Int) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.heightKey] = height
        }
    }

    override suspend fun saveActivityLevel(activityLevel: ActivityLevel) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.activityLevelKey] = activityLevel.name
        }
    }

    override suspend fun saveGoalType(goalType: GoalType) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.goalTypeKey] = goalType.name
        }
    }

    override suspend fun saveCarbRatio(carbRatio: Float) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.carbRatioKey] = carbRatio
        }
    }

    override suspend fun saveProteinRatio(proteinRatio: Float) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.proteinRationKey] = proteinRatio
        }
    }

    override suspend fun saveFatRatio(fatRatio: Float) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.fatRatioKey] = fatRatio
        }
    }

    override fun loadUserInfo(): Flow<UserInfo> {

        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val gender = preferences[PreferencesKey.genderKey] ?: Gender.MALE
                val age = preferences[PreferencesKey.ageKey] ?: -1
                val weight = preferences[PreferencesKey.weightKey] ?: 0.0f
                val height = preferences[PreferencesKey.heightKey] ?: -1
                val activityLevel =
                    preferences[PreferencesKey.activityLevelKey] ?: ActivityLevel.MEDIUM
                val goalType = preferences[PreferencesKey.goalTypeKey] ?: GoalType.KEEP_WEIGHT
                val carbRatio = preferences[PreferencesKey.carbRatioKey] ?: 0.0f
                val proteinRatio = preferences[PreferencesKey.proteinRationKey] ?: 0.0f
                val fatRatio = preferences[PreferencesKey.fatRatioKey] ?: 0.0f
                UserInfo(
                    gender = gender as Gender,
                    age = age,
                    weight = weight,
                    height = height,
                    activityLevel = activityLevel as ActivityLevel,
                    goalType = goalType as GoalType,
                    carbRatio = carbRatio,
                    proteinRatio = proteinRatio,
                    fatRatio = fatRatio
                )
            }
    }

    override suspend fun saveShouldShowOnboarding(showOnboarding: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.showOnboarding] = showOnboarding
        }
    }

    override fun loadShouldShowOnboarding(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val showOnboarding = preferences[PreferencesKey.showOnboarding] ?: false
                showOnboarding
            }
    }

}





