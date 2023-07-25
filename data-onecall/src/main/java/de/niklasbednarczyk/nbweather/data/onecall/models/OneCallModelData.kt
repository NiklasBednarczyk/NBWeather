package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallModelLocal

data class OneCallModelData(
    val currentWeather: CurrentWeatherModelData,
    val minutelyForecasts: List<MinutelyForecastModelData>,
    val hourlyForecasts: List<HourlyForecastModelData>,
    val dailyForecasts: List<DailyForecastModelData>,
    val nationalWeatherAlerts: List<NationalWeatherAlertModelData>
) {

    val today = dailyForecasts.firstOrNull()

    companion object {

        fun localToData(local: OneCallModelLocal): OneCallModelData {
            val timezoneOffset = local.metadata.timezoneOffset

            return OneCallModelData(
                currentWeather = CurrentWeatherModelData.localToData(local.currentWeather, timezoneOffset),
                minutelyForecasts = MinutelyForecastModelData.localToData(local.minutelyForecasts, timezoneOffset),
                hourlyForecasts = HourlyForecastModelData.localToData(local.hourlyForecasts, timezoneOffset),
                dailyForecasts = DailyForecastModelData.localToData(local.dailyForecasts, timezoneOffset),
                nationalWeatherAlerts = NationalWeatherAlertModelData.localToData(local.nationalWeatherAlerts, timezoneOffset)
            )
        }

    }

}