package com.example.tracker_presentation.tracker_overview_screen

import com.example.tracker_domain.model.TrackedFood

sealed class TrackerOverviewEvent{
    object OnNextDayClicked: TrackerOverviewEvent()
    object OnPreviousDayClicked: TrackerOverviewEvent()
    data class OnToggleMealClicked(val meal: Meal): TrackerOverviewEvent()
    data class OnDeleteTrackedFoodClicked(val trackedFood: TrackedFood): TrackerOverviewEvent()
    data class OnAddFoodClicked(val meal: Meal): TrackerOverviewEvent()
}