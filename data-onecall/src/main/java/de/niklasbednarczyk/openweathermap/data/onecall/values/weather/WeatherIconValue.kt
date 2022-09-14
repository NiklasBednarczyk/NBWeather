package de.niklasbednarczyk.openweathermap.data.onecall.values.weather

import de.niklasbednarczyk.openweathermap.core.data.localremote.remote.constants.ConstantsCoreRemote

@JvmInline
value class WeatherIconValue(private val value: String?) {

    val url: String?
        get() {
            if (value == null) return null
            return "${ConstantsCoreRemote.ImageUrl.PREFIX}$value${ConstantsCoreRemote.ImageUrl.SUFFIX}"
        }

}