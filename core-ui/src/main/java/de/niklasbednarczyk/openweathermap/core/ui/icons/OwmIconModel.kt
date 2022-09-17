package de.niklasbednarczyk.openweathermap.core.ui.icons

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

interface OwmIconModel {

    val imageVector: ImageVector

    @get:StringRes
    val contentDescriptionResId: Int

}