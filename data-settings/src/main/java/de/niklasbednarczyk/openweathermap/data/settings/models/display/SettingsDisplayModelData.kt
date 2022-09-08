package de.niklasbednarczyk.openweathermap.data.settings.models.display

import de.niklasbednarczyk.openweathermap.core.common.display.DataLanguageType
import de.niklasbednarczyk.openweathermap.core.common.display.UnitsType

data class SettingsDisplayModelData(
    val units: UnitsType,
    val dataLanguage: DataLanguageType
)
