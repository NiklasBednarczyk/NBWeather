package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimestampValue
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallMetadataEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallMetadataEntityLocal.Companion.coordinates
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.OneCallModelRemote

data class OneCallModelData(
    val coordinates: NBCoordinatesModel,
    val timestamp: NBTimestampValue,
    val timezoneOffset: NBTimezoneOffsetValue?,
    val currentWeather: CurrentWeatherModelData,
    val minutelyForecasts: List<MinutelyForecastModelData>,
    val hourlyForecasts: List<HourlyForecastModelData>,
    val dailyForecasts: List<DailyForecastModelData>,
    val nationalWeatherAlerts: List<NationalWeatherAlertModelData>
) {

    val today = dailyForecasts.firstOrNull()

    internal companion object {

        fun localToData(
            local: OneCallModelLocal
        ): OneCallModelData {
            return OneCallModelData(
                coordinates = local.metadata.coordinates,
                timestamp = NBTimestampValue(local.metadata.timestampEpochSeconds),
                timezoneOffset = NBTimezoneOffsetValue.from(local.metadata.timezoneOffset),
                currentWeather = CurrentWeatherModelData.localToData(local.currentWeather),
                minutelyForecasts = MinutelyForecastModelData.localToData(local.minutelyForecasts),
                hourlyForecasts = HourlyForecastModelData.localToData(local.hourlyForecasts),
                dailyForecasts = DailyForecastModelData.localToData(local.dailyForecasts),
                nationalWeatherAlerts = NationalWeatherAlertModelData.localToData(local.nationalWeatherAlerts)
            )
        }

        fun remoteToLocal(
            remote: OneCallModelRemote,
            latitude: Double,
            longitude: Double
        ): OneCallMetadataEntityLocal {
            return OneCallMetadataEntityLocal(
                latitude = latitude,
                longitude = longitude,
                timezone = remote.timezone,
                timezoneOffset = remote.timezoneOffset
            )
        }

    }

}