package de.niklasbednarczyk.nbweather.feature.about.screens.overview.models

import android.content.Intent
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel

data class AboutOverviewButtonModel(
    val label: NBString?,
    val icon: NBIconModel,
    val intent: Intent
)