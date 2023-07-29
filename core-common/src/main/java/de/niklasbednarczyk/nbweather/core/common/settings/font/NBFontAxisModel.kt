package de.niklasbednarczyk.nbweather.core.common.settings.font

interface NBFontAxisModel {
    val name: String
    val defaultValue: Float
    val minValue: Float
    val maxValue: Float
    val fractionDigits: Int
}