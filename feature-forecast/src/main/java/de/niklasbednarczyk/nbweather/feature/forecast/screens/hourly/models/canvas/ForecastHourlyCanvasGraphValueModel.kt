package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas

import androidx.compose.ui.text.TextLayoutResult

data class ForecastHourlyCanvasGraphValueModel(
    val factor: Float,
    val displayValue: TextLayoutResult
)