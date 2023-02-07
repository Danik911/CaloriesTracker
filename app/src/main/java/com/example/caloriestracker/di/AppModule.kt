package com.example.caloriestracker.di

import android.content.Context
import com.example.core.data.preferences.DataStorePreferencesImpl
import com.example.core.domain.preferences.DataStorePreferences
import com.example.core.domain.use_cases.FilterOutDigitsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataStorePreferences(
        @ApplicationContext context: Context
    ): DataStorePreferences {
        return DataStorePreferencesImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideFilterOutDigitsUseCase(): FilterOutDigitsUseCase{
        return FilterOutDigitsUseCase()
    }
}