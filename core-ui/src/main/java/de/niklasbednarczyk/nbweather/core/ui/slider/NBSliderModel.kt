package de.niklasbednarczyk.nbweather.core.ui.slider

import de.niklasbednarczyk.nbweather.core.common.string.NBString

data class NBSliderModel(
    val title: NBString?,
    val value: Float,
    val minValue: Float,
    val maxValue: Float,
    val fractionDigits: Int,
    val onValueChange: (Float) -> Unit,
    val onValueChangeFinished: (() -> Unit)? = null
)
