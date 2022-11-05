package de.niklasbednarczyk.openweathermap.data.settings.models.display

import de.niklasbednarczyk.openweathermap.core.common.display.OwmDataLanguageType
import de.niklasbednarczyk.openweathermap.core.common.display.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.display.OwmUnitsType

data class SettingsDisplayModelData(
    val dataLanguage: OwmDataLanguageType,
    val units: OwmUnitsType,
    val timeFormat: OwmTimeFormatType
)
