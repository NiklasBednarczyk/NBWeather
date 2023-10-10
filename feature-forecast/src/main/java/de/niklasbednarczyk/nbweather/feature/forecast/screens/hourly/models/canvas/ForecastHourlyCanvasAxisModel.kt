package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas

import androidx.compose.ui.text.TextLayoutResult
import de.niklasbednarczyk.nbweather.core.common.string.NBString

data class ForecastHourlyCanvasAxisModel(
    val headlineText: NBString?,
    val dayOfMonth: Int,
    val time: TextLayoutResult
)