package com.example.onboarding_presentation.age_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.R.string.*
import com.example.core.domain.preferences.DataStorePreferences
import com.example.core.domain.use_cases.FilterOutDigitsUseCase
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val dataStorePreferences: DataStorePreferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase
) : ViewModel() {


    var age by mutableStateOf("20")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAgeEnter(age: String) {
        if (age.length <= 2) {
            this.age = filterOutDigitsUseCase(age)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {

            val ageNumber = age.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(error_age_cant_be_empty)
                    )
                )
                return@launch
            }
            Timber.d("$ageNumber")
           dataStorePreferences.saveAge(age = ageNumber)
            _uiEvent.send(UiEvent.Navigate(Route.HEIGHT))
        }
    }
}