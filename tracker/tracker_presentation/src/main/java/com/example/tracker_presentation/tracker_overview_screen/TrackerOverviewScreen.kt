package com.example.tracker_presentation.tracker_overview_screen

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.tracker_presentation.tracker_overview_screen.components.DaySelector
import com.example.tracker_presentation.tracker_overview_screen.components.ExpandableMeal
import com.example.tracker_presentation.tracker_overview_screen.components.NutrientsHeader


@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader(state = state)
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            DaySelector(
                date = state.date,
                onPrevDayClick = { viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClicked) },
                onNextDayClick = { viewModel.onEvent(TrackerOverviewEvent.OnNextDayClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
        items(state.meals) { meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = { viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClicked(meal)) },
                content = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}