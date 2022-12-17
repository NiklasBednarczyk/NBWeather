package de.niklasbednarczyk.nbweather.core.ui.image

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.theme.NBTheme

@Composable
fun NBImage(
    modifier: Modifier = Modifier,
    image: NBImageModel
) {
    val resId = if (NBTheme.isLightTheme) {
        image.resIdLight
    } else {
        image.resIdDark
    }

    Image(
        modifier = modifier,
        painter = painterResource(resId),
        contentDescription = image.contentDescription.asString()
    )

}