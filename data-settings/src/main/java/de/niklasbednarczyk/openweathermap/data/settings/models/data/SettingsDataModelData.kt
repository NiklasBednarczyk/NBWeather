package de.niklasbednarczyk.openweathermap.data.settings.models.data

import de.niklasbednarczyk.openweathermap.core.common.data.OwmLanguageType
import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType

data class SettingsDataModelData(
    val language: OwmLanguageType,
    val units: OwmUnitsType,
    val timeFormat: OwmTimeFormatType
)
