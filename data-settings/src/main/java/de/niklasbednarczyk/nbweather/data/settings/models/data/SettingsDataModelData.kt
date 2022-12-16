package de.niklasbednarczyk.nbweather.data.settings.models.data

import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType

data class SettingsDataModelData(
    val language: NBLanguageType,
    val units: NBUnitsType,
    val timeFormat: NBTimeFormatType
)
