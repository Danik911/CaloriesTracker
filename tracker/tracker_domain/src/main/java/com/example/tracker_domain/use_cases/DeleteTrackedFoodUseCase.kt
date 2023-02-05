package com.example.tracker_domain.use_cases

import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository

class DeleteTrackedFoodUseCase(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(trackedFood: TrackedFood){
        repository.deleteTrackedFood(food = trackedFood)
    }
}