package de.niklasbednarczyk.nbweather.feature.about.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.image.NBImageItem

sealed interface AboutOverviewItem {

    data object Divider : AboutOverviewItem

    data class WithBanner(
        val banner: NBImageItem,
        val text: NBString?,
        val buttons: List<AboutOverviewButtonModel>
    ) : AboutOverviewItem

    data class WithoutBanner(
        val text: NBString?,
        val buttons: List<AboutOverviewButtonModel>
    ) : AboutOverviewItem

}