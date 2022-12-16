package de.niklasbednarczyk.nbweather.feature.location.cards.models

import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.models.MinutelyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.NationalWeatherAlertModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.datetime.TimezoneOffsetValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.nbweather.feature.location.cards.models.overview.LocationCardOverviewAlertModel
import de.niklasbednarczyk.nbweather.feature.location.cards.models.overview.LocationCardOverviewPrecipitationModel
import de.niklasbednarczyk.nbweather.feature.location.cards.models.overview.LocationCardOverviewWeatherModel

sealed interface LocationCardOverviewItem : LocationCardItem {

    val weather: LocationCardOverviewWeatherModel

    data class JustWeather(
        override val cardTitle: NBString?,
        override val weather: LocationCardOverviewWeatherModel
    ): LocationCardOverviewItem

    data class WithAlertsAndPrecipitation(
        override val cardTitle: NBString?,
        override val weather: LocationCardOverviewWeatherModel,
        val alert: LocationCardOverviewAlertModel?,
        val precipitation: LocationCardOverviewPrecipitationModel
    ): LocationCardOverviewItem

    companion object {

        fun from(
            timeFormat: NBTimeFormatType,
            units: NBUnitsType,
            timezoneOffset: TimezoneOffsetValue?,
            temperature: TemperatureValue?,
            feelsLikeTemperature: TemperatureValue?,
            weather: WeatherModelData?,
            alerts: List<NationalWeatherAlertModelData>?,
            minutelyForecasts: List<MinutelyForecastModelData>?
        ): LocationCardOverviewItem? {
            val cardTitle = NBString.Resource(R.string.screen_location_card_overview_title)

            val weatherModel = LocationCardOverviewWeatherModel.from(
                units = units,
                temperature = temperature,
                feelsLikeTemperature = feelsLikeTemperature,
                weather = weather
            )

            return nbNullSafe(weatherModel) { weatherM ->
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