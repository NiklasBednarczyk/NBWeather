package de.niklasbednarczyk.nbweather.core.ui.image

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R

object NBImages {

    object OpenWeatherLogo : NBImageModel {

        override val resIdDark: Int
            get() = R.drawable.openweather_negative_logo_rgb

        override val resIdLight: Int
            get() = R.drawable.openweather_master_logo_rgb

        override val contentDescription: NBString
            get() = NBString.ResString(R.string.image_content_description_open_weather_logo)

    }

}