package de.niklasbednarczyk.openweathermap.feature.location.cards.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.data.onecall.models.MinutelyForecastModelData
import de.niklasbednarczyk.openweathermap.data.onecall.models.NationalWeatherAlertModelData
import de.niklasbednarczyk.openweathermap.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.openweathermap.data.onecall.values.datetime.TimezoneOffsetValue
import de.niklasbednarczyk.openweathermap.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.openweathermap.feature.location.cards.models.overview.LocationCardOverviewAlertModel
import de.niklasbednarczyk.openweathermap.feature.location.cards.models.overview.LocationCardOverviewPrecipitationModel
import de.niklasbednarczyk.openweathermap.feature.location.cards.models.overview.LocationCardOverviewWeatherModel

sealed interface LocationCardOverviewItem : LocationCardItem {

    val weather: LocationCardOverviewWeatherModel

    data class JustWeather(
        override val cardTitle: OwmString?,
        override val weather: LocationCardOverviewWeatherModel
    ): LocationCardOverviewItem

    data class WithAlertsAndPrecipitation(
        override val cardTitle: OwmString?,
        override val weather: LocationCardOverviewWeatherModel,
        val alert: LocationCardOverviewAlertModel?,
        val precipitation: LocationCardOverviewPrecipitationModel
    ): LocationCardOverviewItem

    companion object {

        fun from(
            timeFormat: OwmTimeFormatType,
            units: OwmUnitsType,
            timezoneOffset: TimezoneOffsetValue?,
            temperature: TemperatureValue?,
            feelsLikeTemperature: TemperatureValue?,
            weather: WeatherModelData?,
            alerts: List<NationalWeatherAlertModelData>?,
            minutelyForecasts: List<MinutelyForecastModelData>?
        ): LocationCardOverviewItem? {
            val cardTitle = OwmString.Resource(R.string.screen_location_card_overview_title)

            val weatherModel = LocationCardOverviewWeatherModel.from(
                units = units,
                temperature = temperature,
                feelsLikeTemperature = feelsLikeTemperature,
                weather = weather
            )

            return owmNullSafe(weatherModel) { weatherM ->
                if (alerts != null && minutelyForecasts != null) {
                    val alertModel = LocationCardOverviewAlertModel.from(
                        alerts = alerts
                    )
                    val precipitationModel = LocationCardOverviewPrecipitationModel.from(
                        minutelyForecasts = minutelyForecasts,
                        timeFormat = timeFormat,
                        timezoneOffset = timezoneOffset
                    )
                    WithAlertsAndPrecipitation(
                        cardTitle = cardTitle,
                        weather = weatherM,
                        alert = alertModel,
                        precipitation = precipitationModel
                    )
                } else {
                    JustWeather(
                        cardTitle = cardTitle,
                        weather = weatherM
                    )
                }
            }


        }

    }

}