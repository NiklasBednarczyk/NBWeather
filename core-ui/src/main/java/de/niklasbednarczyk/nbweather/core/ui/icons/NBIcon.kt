package de.niklasbednarczyk.nbweather.core.ui.icons

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import de.niklasbednarczyk.nbweather.core.ui.strings.asString

@Composable
fun NBIcon(
    modifier: Modifier = Modifier,
    icon: NBIconModel,
    isFilled: Boolean = true,
) {
    val resId = when (icon) {
        is NBIconModel.Default -> icon.resId
        is NBIconModel.FilledAndOutlined -> if (isFilled) icon.resIdFilled else icon.resIdOutlined
    }

    Icon(
        modifier = modifier,
        painter = painterResource(resId),
        contentDescription = icon.contentDescription.asString()
    )
}