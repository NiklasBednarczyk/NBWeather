package de.niklasbednarczyk.nbweather.core.ui.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.android.material.color.MaterialColors

data class NBColorsModel(
    val precipitation: Color
) {

    companion object {

        private fun harmonize(color: Color, primary: Color) =
            Color(MaterialColors.harmonize(color.toArgb(), primary.toArgb()))

        fun from(colorScheme: ColorScheme): NBColorsModel {
            val primary = colorScheme.primary
            return NBColorsModel(
                precipitation = harmonize(Color(0xFF8080FF), primary)
            )
        }

    }

}