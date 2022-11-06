package de.niklasbednarczyk.openweathermap.core.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R

object OwmIcons {

    object Back : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.ArrowBack
        override val imageVectorOutlined: ImageVector = Icons.Outlined.ArrowBack
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_back)
    }

    object Cancel : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Close
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Close
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_cancel)
    }

    object ColorScheme : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Palette
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Palette
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_color_scheme)
    }

    object DataLanguage : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Translate
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Translate
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_data_language)
    }

    object Delete : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Delete
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Delete
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_delete)
    }

    object Drawer : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Menu
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Menu
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_drawer)
    }

    object FindLocation : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.MyLocation
        override val imageVectorOutlined: ImageVector = Icons.Outlined.MyLocation
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_find_location)
    }

    object ErrorNoInternet : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.CloudOff
        override val imageVectorOutlined: ImageVector = Icons.Outlined.CloudOff
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_error_no_internet)
    }

    object ErrorUnknown : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Error
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Error
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_error_unknown)
    }

    object Location : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Place
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Place
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_location)
    }

    object Search : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Search
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Search
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_search)
    }

    object Settings : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Settings
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Settings
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_settings)
    }

    object Theme : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.DarkMode
        override val imageVectorOutlined: ImageVector = Icons.Outlined.DarkMode
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_theme)
    }

    object TimeFormat : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Schedule
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Schedule
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_time_format)
    }

    object Units : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Thermostat
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Thermostat
        override val contentDescription: OwmString =
            OwmString.Resource(R.string.icon_content_description_units)
    }

}