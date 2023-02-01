package com.example.onboarding_presentation.gender_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Gender
import com.example.core.domain.preferences.DataStorePreferences
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val dataStorePreferences: DataStorePreferences
) : ViewModel() {


    var selectedGender by mutableStateOf<Gender>(Gender.MALE)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGenderClick(gender: Gender) {
        selectedGender = gender
    }

    fun onNextClick() {
        viewModelScope.launch {
            dataStorePreferences.saveGender(gender = selectedGender)
            _uiEvent.send(UiEvent.Navigate(Route.AGE))
        }
    }

}