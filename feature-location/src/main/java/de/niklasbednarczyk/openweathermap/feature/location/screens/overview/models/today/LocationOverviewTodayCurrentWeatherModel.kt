package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueIconModel.Companion.toValueIcon
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridItem
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueItem
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.displayText

data class LocationOverviewTodayCurrentWeatherModel(
    override val cardTitle: OwmString?,
    val items: List<OwmGridItem>
) : LocationOverviewTodayItem {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): LocationOverviewTodayCurrentWeatherModel? {
            val cardTitle =
                OwmString.Resource(R.string.screen_location_overview_today_card_current_weather_title)

            val today = oneCall.today
            val currentWeather = oneCall.currentWeather
            val units = oneCall.metadata.units

            val items = mutableListOf<OwmGridItem>()

            owmNullSafe(today?.probabilityOfPrecipitation) { probabilityOfPrecipitation ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_probability_of_precipitation),
                        valueIcon = OwmIcons.ProbabilityOfPrecipitation.toValueIcon(),
                        value = OwmValueItem.Texts(
                            probabilityOfPrecipitation.displayValue,
                            probabilityOfPrecipitation.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.rain1hVolume) { rain1hVolume ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_rain),
                        valueIcon = OwmIcons.Rain.toValueIcon(),
                        value = OwmValueItem.Texts(
                            rain1hVolume.displayValue,
                            rain1hVolume.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.snow1hVolume) { snow1hVolume ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_snow),
                        valueIcon = OwmIcons.Snow.toValueIcon(),
                        value = OwmValueItem.Texts(
                            snow1hVolume.displayValue,
                            snow1hVolume.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.windSpeed) { windSpeed ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_speed),
                        valueIcon = OwmIcons.WindSpeed.toValueIcon(),
                        value = OwmValueItem.Texts(
                            windSpeed.displayValue,
                            windSpeed.getUnit(units)
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.windGust) { windGust ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_gust),
                        valueIcon = OwmIcons.WindGust.toValueIcon(),
                        value = OwmValueItem.Texts(
                            windGust.displayValue,
                            windGust.getUnit(units)
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.windDegrees) { windDegrees ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_direction),
                        valueIcon = OwmIcons.WindDegrees.toValueIcon(windDegrees.rotationDegrees),
                        value = OwmValueItem.Texts(
                            windDegrees.type?.displayText ?: return@owmNullSafe null
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.pressure) { pressure ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_pressure),
                        valueIcon = OwmIcons.Pressure.toValueIcon(),
                        value = OwmValueItem.Texts(
                            pressure.displayValue,
                            pressure.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.humidity) { humidity ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_humidity),
                        valueIcon = OwmIcons.Humidity.toValueIcon(),
                        value = OwmValueItem.Texts(
                            humidity.displayValue,
                            humidity.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.dewPointTemperature) { dewPointTemperature ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_dew_point),
                        valueIcon = OwmIcons.DewPoint.toValueIcon(),
                        value = OwmValueItem.Texts(
                            dewPointTemperature.displayValue,
                            dewPointTemperature.getUnit(units)
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.cloudiness) { cloudiness ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_cloudiness),
                        valueIcon = OwmIcons.Cloudiness.toValueIcon(),
                        value = OwmValueItem.Texts(
                            cloudiness.displayValue,
                            cloudiness.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.uvIndex) { uvIndex ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_uv_index),
                        valueIcon = OwmIcons.UVIndex.toValueIcon(),
                        value = OwmValueItem.Texts(
                            uvIndex.displayValue,
                            uvIndex.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.visibility) { visibility ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_visibility),
                        valueIcon = OwmIcons.Visibility.toValueIcon(),
                        value = OwmValueItem.Texts(
                            visibility.displayValue,
                            visibility.unit
                        )
                    )
                )
            }

            return owmNullSafeList(
                items
            ) { i ->
                LocationOverviewTodayCurrentWeatherModel(
                    cardTitle = cardTitle,
                    items = i
                )
            }
        }

    }


}