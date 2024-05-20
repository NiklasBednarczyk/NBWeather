package de.niklasbednarczyk.nbweather

import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBAppearanceModel
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.models.MainViewData

data class MainUiState(
    val appearance: NBAppearanceModel? = null,
    val font: NBFontModel? = null,
    val order: NBOrderModel? = null,
    val units: NBUnitsModel? = null,
    val viewDataResource: NBResource<MainViewData> = NBResource.Loading
)