package de.niklasbednarczyk.nbweather.core.ui.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings
import de.niklasbednarczyk.nbweather.core.ui.strings.asString

@Composable
fun NBImageView(
    modifier: Modifier = Modifier,
    image: NBImageItem?
) {
    if (image != null) {
        val resId = when (image) {
            is NBImageItem.One -> image.resId

            is NBImageItem.Two -> {
                if (NBSettings.isDarkTheme) {
                    image.resIdDark
                } else {
                    image.resIdLight
                }
            }
        }

        Image(
            modifier = modifier,
            painter = painterResource(resId),
            contentDescription = image.contentDescription.asString()
        )
    } else {
        Spacer(modifier = modifier)
    }
}