package de.niklasbednarczyk.openweathermap.feature.location.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.feature.location.R

object LocationIcons {

    object Search : OwmIconModel {
        override val imageVector: ImageVector
            get() = Icons.Default.Search
        override val contentDescriptionResId: Int
            get() = R.string.icon_content_description_search
    }

}