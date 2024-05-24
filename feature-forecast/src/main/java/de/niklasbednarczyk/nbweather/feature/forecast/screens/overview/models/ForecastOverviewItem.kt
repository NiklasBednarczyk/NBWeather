package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.models.CurrentWeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.HourlyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.MinutelyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.NationalWeatherAlertModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData

sealed interface ForecastOverviewItem {

    val title: NBString?
        get() = when (this) {
            is ForecastOverviewAlertsModel,
            is ForecastOverviewSummaryModel -> null

            is ForecastOverviewCurrentWeatherModel -> NBString.ResString(R.string.screen_forecast_overview_current_weather_title)

            is ForecastOverviewDailyModel -> NBString.ResString(R.string.screen_forecast_overview_daily_title)

            is ForecastOverviewHourlyModel -> NBString.ResString(R.string.screen_forecast_overview_hourly_title)

            is ForecastOverviewPrecipitationModel -> NBString.ResString(R.string.screen_forecast_overview_precipitation_title)

            is ForecastOverviewSunAndMoonModel -> NBString.ResString(R.string.screen_forecast_overview_sun_and_moon_title)
        }


    fun getSortOrder(order: NBOrderModel): Int {
        return when (this) {
            is ForecastOverviewAlertsModel -> -2
            is ForecastOverviewCurrentWeatherModel -> order.currentWeatherOrder
            is ForecastOverviewDailyModel -> order.dailyOrder
            is ForecastOverviewHourlyModel -> order.hourlyOrder
            is ForecastOverviewPrecipitationModel -> order.precipitationOrder
            is ForecastOverviewSummaryModel -> -1
            is ForecastOverviewSunAndMoonModel -> order.sunAndMoonOrder
        }
    }

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): List<ForecastOverviewItem>? {
            return from(
                coordinates = oneCall.coordinates,
                timezoneOffset = oneCall.timezoneOffset,
                currentWeather = oneCall.currentWeather,
                minutelyForecasts = oneCall.minutelyForecasts,
                hourlyForecasts = oneCall.hourlyForecasts,
                dailyForecasts = oneCall.dailyForecasts,
                nationalWeatherAlerts = oneCall.nationalWeatherAlerts,
                today = oneCall.today
            )
        }

        fun from(
            coordinates: NBCoordinatesModel,
            timezoneOffset: NBTimezoneOffsetValue?,
            currentWeather: CurrentWeatherModelData?,
            minutelyForecasts: List<MinutelyForecastModelData>,
            hourlyForecasts: List<HourlyForecastModelData>,
            dailyForecasts: List<DailyForecastModelData>,
            nationalWeatherAlerts: List<NationalWeatherAlertModelData>,
            today: DailyForecastModelData?
        ): List<ForecastOverviewItem>? {
            val items = listOfNotNull(
                ForecastOverviewAlertsModel.from(
                    nationalWeatherAlerts = nationalWeatherAlerts
                ),
                ForecastOverviewCurrentWeatherModel.from(
                    currentWeather = currentWeather,
                    today = today
                ),
                ForecastOverviewDailyModel.from(
                    timezoneOffset = timezoneOffset,
                    dailyForecasts = dailyForecasts
                ),
                ForecastOverviewHourlyModel.from(
                    timezoneOffset = timezoneOffset,
                    hourlyForecasts = hourlyForecasts
                ),
                ForecastOverviewPrecipitationModel.from(
                    timezoneOffset = timezoneOffset,
                    minutelyForecasts = minutelyForecasts
                ),
                ForecastOverviewSummaryModel.from(
                    timezoneOffset = timezoneOffset,
                    currentWeather = currentWeather,
                    today = today
                ),
                ForecastOverviewSunAndMoonModel.from(
                    coordinates = coordinates,
                    timezoneOffset = timezoneOffset,
                    currentWeather = currentWeather,
                    today = today
                )
            )
            return nbNullSafeList(items) { i -> i }
        }
    }

}