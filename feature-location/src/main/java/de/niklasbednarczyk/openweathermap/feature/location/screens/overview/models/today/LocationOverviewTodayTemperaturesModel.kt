package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.temperatures.LocationOverviewTodayTemperaturesItem

data class LocationOverviewTodayTemperaturesModel(
    val thresholdItems: List<LocationOverviewTodayTemperaturesItem.Threshold>,
    val dayItems: List<LocationOverviewTodayTemperaturesItem.Day>
) : LocationOverviewTodayItem {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): LocationOverviewTodayTemperaturesModel? {

            return owmNullSafe(
                oneCall.today?.temperature,
                oneCall.today?.feelsLikeTemperature
            ) { temperature, feelsLikeTemperature ->

                val thresholdItems =
                    mutableListOf<LocationOverviewTodayTemperaturesItem.Threshold>()

                temperature.minDailyTemperature?.let { minDailyTemperature ->
                    thresholdItems.add(
                        LocationOverviewTodayTemperaturesItem.Threshold(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_threshold_min_title),
                            temperature = minDailyTemperature.displayValueWithShortUnit
                        )
                    )
                }

                temperature.maxDailyTemperature?.let { maxDailyTemperature ->
                    thresholdItems.add(
                        LocationOverviewTodayTemperaturesItem.Threshold(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_threshold_max_title),
                            temperature = maxDailyTemperature.displayValueWithShortUnit
                        )
                    )
                }

                val dayItems = mutableListOf<LocationOverviewTodayTemperaturesItem.Day>()

                owmNullSafe(
                    temperature.morningTemperature,
                    feelsLikeTemperature.morningTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        LocationOverviewTodayTemperaturesItem.Day(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_morning_title),
                            temperature = temp.displayValueWithShortUnit,
                            feelsLikeTemperature = feelsLikeTemp.displayValueWithShortUnit
                        )
                    )
                }

                owmNullSafe(
                    temperature.dayTemperature,
                    feelsLikeTemperature.dayTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        LocationOverviewTodayTemperaturesItem.Day(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_day_title),
                            temperature = temp.displayValueWithShortUnit,
                            feelsLikeTemperature = feelsLikeTemp.displayValueWithShortUnit
                        )
                    )
                }

                owmNullSafe(
                    temperature.eveningTemperature,
                    feelsLikeTemperature.eveningTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        LocationOverviewTodayTemperaturesItem.Day(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_evening_title),
                            temperature = temp.displayValueWithShortUnit,
                            feelsLikeTemperature = feelsLikeTemp.displayValueWithShortUnit
                        )
                    )
                }

                owmNullSafe(
                    temperature.nightTemperature,
                    feelsLikeTemperature.nightTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        LocationOverviewTodayTemperaturesItem.Day(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_night_title),
                            temperature = temp.displayValueWithShortUnit,
                            feelsLikeTemperature = feelsLikeTemp.displayValueWithShortUnit
                        )
                    )
                }

                owmNullSafeList(
                    thresholdItems,
                    dayItems
                ) { tItems, dItems ->
                    LocationOverviewTodayTemperaturesModel(
                        thresholdItems = tItems,
                        dayItems = dItems
                    )
                }

            }

        }

    }


}