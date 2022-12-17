package de.niklasbednarczyk.nbweather.feature.about.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.image.NBImageModel

sealed interface AboutOverviewItem {

    object Divider : AboutOverviewItem

    data class WithBanner(
        val banner: NBImageModel,
        val text: NBString?,
        val buttons: List<AboutOverviewButtonModel>
    ) : AboutOverviewItem

    data class WithoutBanner(
        val text: NBString?,
        val buttons: List<AboutOverviewButtonModel>
    ) : AboutOverviewItem

}