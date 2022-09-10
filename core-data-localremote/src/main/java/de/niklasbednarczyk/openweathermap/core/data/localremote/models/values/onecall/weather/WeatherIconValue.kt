package de.niklasbednarczyk.openweathermap.core.data.localremote.models.values.onecall.weather

import de.niklasbednarczyk.openweathermap.core.data.localremote.remote.constants.ConstantsCoreRemote

@JvmInline
value class WeatherIconValue(private val value: String?) {

    val url: String?
        get() {
            if (value == null) return null
            return "${ConstantsCoreRemote.ImageUrl.PREFIX}$value${ConstantsCoreRemote.ImageUrl.SUFFIX}"
        }

}