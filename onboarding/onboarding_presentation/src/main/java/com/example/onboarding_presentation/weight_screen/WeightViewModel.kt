package com.example.onboarding_presentation.weight_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.R
import com.example.core.domain.preferences.DataStorePreferences
import com.example.core.domain.use_cases.FilterOutDigits
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val dataStorePreferences: DataStorePreferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {


    var weight by mutableStateOf("80.0")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightClicked(weight: String) {
        if(weight.length <= 5) {
            this.weight = filterOutDigits(weight)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val height = weight.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.error_weight_cant_be_empty)
                    )
                )
                return@launch
            }
            dataStorePreferences.saveHeight(height)
            _uiEvent.send(UiEvent.Navigate(Route.ACTIVITY))
        }
    }
}