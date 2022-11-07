package de.niklasbednarczyk.openweathermap.core.ui.icons

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString

@Composable
fun OwmIcon(
    modifier: Modifier = Modifier,
    icon: OwmIconModel,
    isFilled: Boolean = true,
) {
    val resId = when (icon) {
        is OwmIconModel.Default -> icon.resId
        is OwmIconModel.FilledAndOutlined -> if (isFilled) icon.resIdFilled else icon.resIdOutlined
    }

    Icon(
        modifier = modifier,
        painter = painterResource(resId),
        contentDescription = icon.contentDescription.asString()
    )
}