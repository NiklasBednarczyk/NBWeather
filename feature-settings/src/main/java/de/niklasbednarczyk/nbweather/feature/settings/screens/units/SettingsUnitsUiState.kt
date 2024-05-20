package de.niklasbednarczyk.nbweather.feature.settings.screens.units

import de.niklasbednarczyk.nbweather.feature.settings.screens.units.models.SettingsUnitsItemModel

data class SettingsUnitsUiState(
    val items: List<SettingsUnitsItemModel> = emptyList()
)