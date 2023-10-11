package de.niklasbednarczyk.nbweather.feature.settings.screens.order

import de.niklasbednarczyk.nbweather.feature.settings.screens.order.models.SettingsOrderItemType

data class SettingsOrderUiState(
    val items: List<SettingsOrderItemType> = emptyList()
)