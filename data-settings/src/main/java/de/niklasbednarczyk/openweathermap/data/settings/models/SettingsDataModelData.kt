package de.niklasbednarczyk.openweathermap.data.settings.models

import de.niklasbednarczyk.openweathermap.core.common.data.DataLanguageType
import de.niklasbednarczyk.openweathermap.core.common.data.UnitsType

data class SettingsDataModelData(
    val units: UnitsType,
    val dataLanguage: DataLanguageType
)
