package com.example.onboarding_presentation.nutrient_goal_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.use_cases.FilterOutDigitsUseCase
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.onboarding_domain.use_cases.ValidateNutrientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase,
    private val validateNutrientsUseCase: ValidateNutrientsUseCase
) : ViewModel() {


    var state by mutableStateOf(NutrientGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbsRatioEnter -> {
                state = state.copy(carbsRatio = filterOutDigitsUseCase(event.carbsRatio))
            }
            is NutrientGoalEvent.OnProteinRatioEnter -> {
                state = state.copy(proteinRatio = filterOutDigitsUseCase(event.proteinRatio))
            }
            is NutrientGoalEvent.OnFatRatioEnter -> {
                state = state.copy(fatRatio = filterOutDigitsUseCase(event.fatRatio))
            }
            is NutrientGoalEvent.OnNextClick -> validateResult()
        }
    }
    private fun validateResult() {
        val result = validateNutrientsUseCase(
            carbsRatioText = state.carbsRatio,
            proteinRatioText = state.proteinRatio,
            fatRatioText = state.fatRatio
        )
        when (result) {
            is ValidateNutrientsUseCase.Result.Success -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(Route.TRACKER_OVERVIEW))
                    preferences.saveCarbRatio(result.carbsRatio)
                    preferences.saveProteinRatio(result.proteinRatio)
                    preferences.saveFatRatio(result.fatRatio)
                }
            }
            is ValidateNutrientsUseCase.Result.Error -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.ShowSnackbar(result.message))
                }
            }

        }
    }
}

