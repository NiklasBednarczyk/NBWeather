package de.niklasbednarczyk.openweathermap.core.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import de.niklasbednarczyk.openweathermap.core.ui.R

object OwmIcons {

    object Back : OwmIconModel {
        override val imageVector: ImageVector
            get() = Icons.Default.ArrowBack
        override val contentDescriptionResId: Int
            get() = R.string.icon_content_description_back
    }

    object Cancel : OwmIconModel {
        override val imageVector: ImageVector
            get() = Icons.Default.Close
        override val contentDescriptionResId: Int
            get() = R.string.icon_content_description_cancel
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

    object Search : OwmIconModel {
        override val imageVector: ImageVector
            get() = Icons.Default.Search
        override val contentDescriptionResId: Int
            get() = R.string.icon_content_description_search
    }

    object Settings : OwmIconModel {
        override val imageVector: ImageVector
            get() = Icons.Default.Settings
        override val contentDescriptionResId: Int
            get() = R.string.icon_content_description_settings
    }

}