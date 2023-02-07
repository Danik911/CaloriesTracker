package com.example.tracker_presentation.tracker_overview_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun LocalDate.parseDateText(): String {
    val today = LocalDate.now()
    return when (this) {
        today -> stringResource(id = com.example.core.R.string.today)
        today.plusDays(1) -> stringResource(id = com.example.core.R.string.tomorrow)
        today.minusDays(1) -> stringResource(id = com.example.core.R.string.yesterday)
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(this)
    }
}