package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel

data class ForecastHourlyAxisModel(
    val forecastTime: NBDateTimeDisplayModel,
    val icon: NBIconModel
)