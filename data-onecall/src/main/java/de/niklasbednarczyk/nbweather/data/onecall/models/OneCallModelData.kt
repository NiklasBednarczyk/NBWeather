package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallModelLocal

data class OneCallModelData(
    val metadata: OneCallMetadataModelData,
    val currentWeather: CurrentWeatherModelData,
    val minutelyForecasts: List<MinutelyForecastModelData>,
    val hourlyForecasts: List<HourlyForecastModelData>,
    val dailyForecasts: List<DailyForecastModelData>,
    val nationalWeatherAlerts: List<NationalWeatherAlertModelData>
) {

    val today = dailyForecasts.firstOrNull()

    companion object {

        fun localToData(local: OneCallModelLocal): OneCallModelData {
            return OneCallModelData(
                metadata = OneCallMetadataModelData.localToData(local.metadata),
                currentWeather = CurrentWeatherModelData.localToData(local.currentWeather),
                minutelyForecasts = MinutelyForecastModelData.localToData(local.minutelyForecasts),
                hourlyForecasts = HourlyForecastModelData.localToData(local.hourlyForecasts),
                dailyForecasts = DailyForecastModelData.localToData(local.dailyForecasts),
                nationalWeatherAlerts = NationalWeatherAlertModelData.localToData(local.nationalWeatherAlerts)
            )
        }

    }

}