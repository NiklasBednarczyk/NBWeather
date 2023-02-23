package de.niklasbednarczyk.nbweather.core.ui.theme

import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import com.google.android.material.color.MaterialColors

data class NBCustomColorsModel(
    val blue: Color,
    val green: Color,
    val red: Color,
    val yellow: Color
) {

    companion object {

        fun from(@ColorInt sourceColorInt: Int): NBCustomColorsModel {
            val blueInt = MaterialColors.harmonize(0x0000FF, sourceColorInt)
            val greenInt = MaterialColors.harmonize(0x00FF00, sourceColorInt)
            val redInt = MaterialColors.harmonize(0xFF0000, sourceColorInt)
            val yellowInt = MaterialColors.harmonize(0xFFFF00, sourceColorInt)

            return NBCustomColorsModel(
                blue = Color(blueInt),
                green = Color(greenInt),
                red = Color(redInt),
                yellow = Color(yellowInt)
            )
        }

    }

}