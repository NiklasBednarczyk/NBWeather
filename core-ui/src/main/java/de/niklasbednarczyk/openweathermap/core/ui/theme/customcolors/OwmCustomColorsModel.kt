package de.niklasbednarczyk.openweathermap.core.ui.theme.customcolors

import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import de.niklasbednarczyk.openweathermap.library.materialcolorutilities.blend.Blend
import javax.annotation.concurrent.Immutable

@Immutable
data class OwmCustomColorsModel(
    val green: Color,
    val red: Color,
    val yellow: Color
) {

    companion object {

        fun from(@ColorInt sourceColorInt: Int): OwmCustomColorsModel {
            val greenInt = Blend.harmonize(0x00FF00, sourceColorInt)
            val redInt = Blend.harmonize(0xFF0000, sourceColorInt)
            val yellowInt = Blend.harmonize(0xFFFF00, sourceColorInt)

            return OwmCustomColorsModel(
                green = Color(greenInt),
                red = Color(redInt),
                yellow = Color(yellowInt)
            )
        }

    }

}