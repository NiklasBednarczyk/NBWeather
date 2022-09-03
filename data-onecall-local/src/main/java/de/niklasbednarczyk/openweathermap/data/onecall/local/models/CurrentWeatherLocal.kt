package de.niklasbednarczyk.openweathermap.data.onecall.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.common.OneCallHeaderLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.common.PrecipitationLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.common.WeatherLocal

@Entity
data class CurrentWeatherLocal(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @Embedded(prefix = "oneCallHeader") val oneCallHeader: OneCallHeaderLocal,
    val dt: Long?,
    val sunrise: Long?,
    val sunset: Long?,
    val temp: Double?,
    val feelsLike: Double?,
    val pressure: Long?,
    val humidity: Long?,
    val dewPoint: Double?,
    val clouds: Long?,
    val uvi: Double?,
    val visibility: Long?,
    val windSpeed: Double?,
    val windGust: Double?,
    val windDeg: Long?,
    @Embedded(prefix = "rain") val rain: PrecipitationLocal?,
    @Embedded(prefix = "snow") val snow: PrecipitationLocal?,
    @Embedded(prefix = "weather") val weather: WeatherLocal?
)