package de.niklasbednarczyk.openweathermap.data.onecall.local.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.common.WeatherModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.daily.DailyFeelsLikeTemperatureModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.daily.DailyTemperatureModelLocal

@Entity
data class DailyForecastEntityLocal(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY) val metadataId: Long?,
    val dt: Long?,
    val sunrise: Long?,
    val sunset: Long?,
    val moonrise: Long?,
    val moonset: Long?,
    val moonPhase: Double?,
    @Embedded(prefix = "temp_") val temp: DailyTemperatureModelLocal?,
    @Embedded(prefix = "feelsLike_") val feelsLike: DailyFeelsLikeTemperatureModelLocal?,
    val pressure: Long?,
    val humidity: Long?,
    val dewPoint: Double?,
    val windSpeed: Double?,
    val windGust: Double?,
    val windDeg: Long?,
    val clouds: Long?,
    val uvi: Double?,
    val pop: Double?,
    val rain: Double?,
    val snow: Double?,
    @Embedded(prefix = "weather_") val weather: WeatherModelLocal?
)