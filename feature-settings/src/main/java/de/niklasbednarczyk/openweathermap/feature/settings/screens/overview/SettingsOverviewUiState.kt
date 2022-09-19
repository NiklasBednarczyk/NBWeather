package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import de.niklasbednarczyk.openweathermap.data.settings.models.units.SettingsUnitsModelData

data class SettingsOverviewUiState(
    val settingsUnits: SettingsUnitsModelData? = null
)