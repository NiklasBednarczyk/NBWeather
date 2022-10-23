package de.niklasbednarczyk.openweathermap.core.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.uitext.OwmStringResource
import de.niklasbednarczyk.openweathermap.core.ui.uitext.OwmUiText

object OwmIcons {

    object Back : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.ArrowBack
        override val imageVectorOutlined: ImageVector = Icons.Outlined.ArrowBack
        override val contentDescription: OwmUiText =
            OwmStringResource(R.string.icon_content_description_back)
    }

    object Cancel : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Close
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Close
        override val contentDescription: OwmUiText =
            OwmStringResource(R.string.icon_content_description_cancel)
    }

    object Drawer : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Menu
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Menu
        override val contentDescription: OwmUiText =
            OwmStringResource(R.string.icon_content_description_drawer)
    }

    object FindLocation : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.MyLocation
        override val imageVectorOutlined: ImageVector = Icons.Outlined.MyLocation
        override val contentDescription: OwmUiText =
            OwmStringResource(R.string.icon_content_description_find_location)
    }

    object ErrorNoInternet : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.CloudOff
        override val imageVectorOutlined: ImageVector = Icons.Outlined.CloudOff
        override val contentDescription: OwmUiText =
            OwmStringResource(R.string.icon_content_description_error_no_internet)
    }

    object ErrorUnknown : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Error
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Error
        override val contentDescription: OwmUiText =
            OwmStringResource(R.string.icon_content_description_error_unknown)
    }

    object Location : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Place
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Place
        override val contentDescription: OwmUiText =
            OwmStringResource(R.string.icon_content_description_location)
    }

    object Search : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Search
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Search
        override val contentDescription: OwmUiText =
            OwmStringResource(R.string.icon_content_description_search)
    }

    object Settings : OwmIconModel {
        override val imageVectorFilled: ImageVector = Icons.Filled.Settings
        override val imageVectorOutlined: ImageVector = Icons.Outlined.Settings
        override val contentDescription: OwmUiText =
            OwmStringResource(R.string.icon_content_description_settings)
    }

}