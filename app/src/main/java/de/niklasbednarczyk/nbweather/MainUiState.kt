package de.niklasbednarczyk.nbweather

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBAppearanceModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.navigation.NBNavigationDrawerItem

data class MainUiState(
    val appearance: NBAppearanceModel? = null,
    val units: NBUnitsModel? = null,
    val drawerItems: List<NBNavigationDrawerItem> = emptyList(),
    val isInitialCurrentLocationSetResource: NBResource<Boolean>? = null
)