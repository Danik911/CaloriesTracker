package com.example.tracker_domain.di

import com.example.core.domain.preferences.Preferences
import com.example.tracker_domain.repository.TrackerRepository
import com.example.tracker_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModel {

    @ViewModelScoped
    @Provides
    fun provideTrackedUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFoodUseCase = TrackFoodUseCase(repository),
            searchFoodUseCase = SearchFoodUseCase(repository),
            getFoodsForDateUseCase = GetFoodsForDateUseCase(repository),
            deleteTrackedFoodUseCase = DeleteTrackedFoodUseCase(repository),
            calculateMealNutrientsUseCase = CalculateMealNutrientsUseCase(preferences)
        )
    }
}