package com.example.tracker_presentation.tracker_overview_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.DataStorePreferences
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.tracker_domain.use_cases.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    private val dataStorePreferences: DataStorePreferences,
    private val useCases: TrackerUseCases
) : ViewModel() {


    var state by mutableStateOf(TrackerOverviewState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getFoodsForDateJob: Job? = null

    init {
        saveOnboardingState()
    }

    fun onEvent(event: TrackerOverviewEvent) {
        when (event) {
            is TrackerOverviewEvent.OnAddFoodClicked -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Navigate(
                            route = Route.SEARCH
                                    + "/${event.meal.mealType.name}"
                                    + "/${state.date.dayOfMonth}"
                                    + "/${state.date.monthValue}"
                                    + "/${state.date.year}"
                        )
                    )
                }
            }
            is TrackerOverviewEvent.OnDeleteTrackedFoodClicked -> {
                viewModelScope.launch {
                    useCases.deleteTrackedFoodUseCase(event.trackedFood)
                }
            }
            is TrackerOverviewEvent.OnNextDayClicked -> {
                state = state.copy(date = state.date.plusDays(1))
            }
            is TrackerOverviewEvent.OnPreviousDayClicked -> {
                state = state.copy(date = state.date.minusDays(1))
            }
            is TrackerOverviewEvent.OnToggleMealClicked -> {
                state = state.copy(
                    meals = state.meals.map {
                        if (it.name == event.meal.name){
                            it.copy(isExpanded = !it.isExpanded)
                        } else it
                    }
                )
            }
        }
    }

    private fun  refreshFoods(){
        getFoodsForDateJob?.cancel()
        getFoodsForDateJob = useCases
            .getFoodsForDateUseCase(state.date)
            .onEach { foods ->
                useCases.calculateMealNutrientsUseCase(foods).map {nutrientsResult ->
                    state = state.copy(
                        totalCarbs = nutrientsResult.totalCarbs,
                        totalProtein = nutrientsResult.totalProtein,
                        totalFat = nutrientsResult.totalFat,
                        totalCalories = nutrientsResult.totalCalories,
                        carbsGoal = nutrientsResult.carbsGoal,
                        proteinGoal = nutrientsResult.proteinGoal,
                        fatGoal = nutrientsResult.fatGoal,
                        caloriesGoal = nutrientsResult.caloriesGoal,
                        trackedFoods = foods,
                        meals = state.meals.map {meal ->
                            val nutrientsForMeal =
                                nutrientsResult.mealNutrients[meal.mealType]
                                    ?: return@map meal.copy(
                                        carbs = 0,
                                        protein = 0,
                                        fat = 0,
                                        calories = 0
                                    )
                            meal.copy(
                                carbs = nutrientsForMeal.carbs,
                                protein = nutrientsForMeal.protein,
                                fat = nutrientsForMeal.fat,
                                calories = nutrientsForMeal.calories
                            )
                        }
                    )
                }

            }
            .launchIn(viewModelScope)
    }

    private fun saveOnboardingState() {
        viewModelScope.launch {
            dataStorePreferences.saveShouldShowOnboarding(false)
        }
    }

}