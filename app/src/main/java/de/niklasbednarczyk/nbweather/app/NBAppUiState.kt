package de.niklasbednarczyk.nbweather.app

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.nbweather.navigation.NBNavigationDrawerItem

data class NBAppUiState(
    val settingsAppearance: SettingsAppearanceModelData? = null,
    val drawerItems: List<NBNavigationDrawerItem> = emptyList(),
    val isInitialCurrentLocationSetResource: NBResource<Boolean>? = null,
)