package de.niklasbednarczyk.openweathermap.core.ui.icons

import androidx.compose.ui.graphics.vector.ImageVector
import de.niklasbednarczyk.openweathermap.core.ui.uitext.OwmUiText

interface OwmIconModel {

    val imageVectorFilled: ImageVector

    val imageVectorOutlined: ImageVector

    val contentDescription: OwmUiText

}