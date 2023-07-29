package de.niklasbednarczyk.nbweather.core.common.settings.font

object NBFontAxes {

    object AscenderHeight : NBFontAxisModel {
        override val name = "YTAS"
        override val defaultValue = 750f
        override val minValue = 649f
        override val maxValue = 854f
        override val fractionDigits = 0
    }

    object CounterWidth : NBFontAxisModel {
        override val name = "XTRA"
        override val defaultValue = 468f
        override val minValue = 323f
        override val maxValue = 603f
        override val fractionDigits = 0
    }

    object DescenderDepth : NBFontAxisModel {
        override val name = "YTDE"
        override val defaultValue = -203f
        override val minValue = -305f
        override val maxValue = -98f
        override val fractionDigits = 0
    }

    object FigureHeight : NBFontAxisModel {
        override val name = "YTFI"
        override val defaultValue = 738f
        override val minValue = 560f
        override val maxValue = 788f
        override val fractionDigits = 0
    }

    object Grade : NBFontAxisModel {
        override val name = "GRAD"
        override val defaultValue = 0f
        override val minValue = -200f
        override val maxValue = 150f
        override val fractionDigits = 0
    }

    object LowercaseHeight : NBFontAxisModel {
        override val name = "YTLC"
        override val defaultValue = 514f
        override val minValue = 416f
        override val maxValue = 570f
        override val fractionDigits = 0
    }

    object Slant : NBFontAxisModel {
        override val name = "slnt"
        override val defaultValue = 0f
        override val minValue = -10f
        override val maxValue = 0f
        override val fractionDigits = 0
    }

    object ThinStroke : NBFontAxisModel {
        override val name = "YOPQ"
        override val defaultValue = 79f
        override val minValue = 25f
        override val maxValue = 135f
        override val fractionDigits = 0
    }

    object UppercaseHeight : NBFontAxisModel {
        override val name = "YTUC"
        override val defaultValue = 712f
        override val minValue = 528f
        override val maxValue = 760f
        override val fractionDigits = 0
    }

    object Weight : NBFontAxisModel {
        override val name = "wght"
        override val defaultValue = 400f
        override val minValue = 100f
        override val maxValue = 1000f
        override val fractionDigits = 0
    }

    object Width : NBFontAxisModel {
        override val name = "wdth"
        override val defaultValue = 100f
        override val minValue = 25f
        override val maxValue = 151f
        override val fractionDigits = 1
    }

}