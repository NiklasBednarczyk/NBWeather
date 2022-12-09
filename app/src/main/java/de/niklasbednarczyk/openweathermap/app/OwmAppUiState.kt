package de.niklasbednarczyk.openweathermap.app

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmErrorType
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.core.ui.uistate.OwmResourceUiState
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.openweathermap.navigation.OwmNavigationDrawerItem

data class OwmAppUiState(
    override val errorType: OwmErrorType? = null,
    val settingsAppearance: SettingsAppearanceModelData? = null,
    val drawerItems: List<OwmNavigationDrawerItem> = emptyList(),
    val isInitialCurrentLocationSet: Boolean? = null
): OwmResourceUiState