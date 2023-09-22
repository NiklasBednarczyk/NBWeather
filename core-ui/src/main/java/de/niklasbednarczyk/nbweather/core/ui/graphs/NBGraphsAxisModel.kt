package de.niklasbednarczyk.nbweather.core.ui.graphs

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel

data class NBGraphsAxisModel(
    val forecastTime: NBDateTimeDisplayModel,
    val icon: NBIconModel
)