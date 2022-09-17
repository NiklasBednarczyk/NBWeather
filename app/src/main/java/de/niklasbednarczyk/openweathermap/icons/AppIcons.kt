package de.niklasbednarczyk.openweathermap.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import de.niklasbednarczyk.openweathermap.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel

object AppIcons {

    object Back : OwmIconModel {
        override val imageVector: ImageVector
            get() = Icons.Default.ArrowBack
        override val contentDescriptionResId: Int
            get() = R.string.icon_content_description_back
    }

    object Drawer : OwmIconModel {
        override val imageVector: ImageVector
            get() = Icons.Default.Menu
        override val contentDescriptionResId: Int
            get() = R.string.icon_content_description_drawer
    }

    object Location : OwmIconModel {
        override val imageVector: ImageVector
            get() = Icons.Default.Place
        override val contentDescriptionResId: Int
            get() = R.string.icon_content_description_location
    }

    object Settings : OwmIconModel {
        override val imageVector: ImageVector
            get() = Icons.Default.Settings
        override val contentDescriptionResId: Int
            get() = R.string.icon_content_description_settings
    }

}