package de.niklasbednarczyk.nbweather.core.ui.theme

import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import de.niklasbednarczyk.nbweather.library.materialcolorutilities.blend.Blend

data class NBCustomColorsModel(
    val blue: Color,
    val green: Color,
    val red: Color,
    val yellow: Color
) {

    companion object {

        fun from(@ColorInt sourceColorInt: Int): NBCustomColorsModel {
            val blueInt = Blend.harmonize(0x0000FF, sourceColorInt)
            val greenInt = Blend.harmonize(0x00FF00, sourceColorInt)
            val redInt = Blend.harmonize(0xFF0000, sourceColorInt)
            val yellowInt = Blend.harmonize(0xFFFF00, sourceColorInt)

            return NBCustomColorsModel(
                blue = Color(blueInt),
                green = Color(greenInt),
                red = Color(redInt),
                yellow = Color(yellowInt)
            )
        }

    }

}