package de.niklasbednarczyk.openweathermap.data.onecall.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.common.OneCallHeaderModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.common.PrecipitationModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.common.WeatherModelLocal

@Entity
data class CurrentWeatherModelLocal(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @Embedded(prefix = "oneCallHeader") val oneCallHeader: OneCallHeaderModelLocal,
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
    @Embedded(prefix = "rain") val rain: PrecipitationModelLocal?,
    @Embedded(prefix = "snow") val snow: PrecipitationModelLocal?,
    @Embedded(prefix = "weather") val weather: WeatherModelLocal?
)