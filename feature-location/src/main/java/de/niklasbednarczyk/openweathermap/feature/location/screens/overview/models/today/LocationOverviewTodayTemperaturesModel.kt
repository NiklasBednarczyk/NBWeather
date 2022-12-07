package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridItem
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

data class LocationOverviewTodayTemperaturesModel(
    val thresholdItems: List<OwmGridItem>,
    val dayItems: List<OwmGridItem>
) : LocationOverviewTodayItem {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): LocationOverviewTodayTemperaturesModel? {

            val units = oneCall.metadata.units

            return owmNullSafe(
                oneCall.today?.temperature,
                oneCall.today?.feelsLikeTemperature
            ) { temperature, feelsLikeTemperature ->

                val thresholdItems = mutableListOf<OwmGridItem>()

                owmNullSafe(temperature.minDailyTemperature) { minDailyTemperature ->
                    thresholdItems.add(
                        OwmGridItem.Item(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_threshold_min_title),
                            icon = null,
                            value = OwmGridItem.Value.Texts(
                                minDailyTemperature.displayValue,
                                minDailyTemperature.getUnit(units)
                            )
                        )
                    )
                }

                owmNullSafe(temperature.maxDailyTemperature) { maxDailyTemperature ->
                    thresholdItems.add(
                        OwmGridItem.Item(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_threshold_max_title),
                            icon = null,
                            value = OwmGridItem.Value.Texts(
                                maxDailyTemperature.displayValue,
                                maxDailyTemperature.getUnit(units)
                            )
                        )
                    )
                }

                val dayItems = mutableListOf<OwmGridItem>()

                val formatResId = R.string.format_temperature_feels_like

                owmNullSafe(
                    temperature.morningTemperature,
                    feelsLikeTemperature.morningTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmGridItem.Item(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_morning_title),
                            icon = null,
                            value = OwmGridItem.Value.TextsWithFormat(
                                temp.displayValueWithShortUnit,
                                feelsLikeTemp.displayValueWithShortUnit,
                                formatResId = formatResId
                            )
                        )
                    )
                }

                owmNullSafe(
                    temperature.dayTemperature,
                    feelsLikeTemperature.dayTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmGridItem.Item(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_day_title),
                            icon = null,
                            value = OwmGridItem.Value.TextsWithFormat(
                                temp.displayValueWithShortUnit,
                                feelsLikeTemp.displayValueWithShortUnit,
                                formatResId = formatResId
                            )
                        )
                    )
                }

                owmNullSafe(
                    temperature.eveningTemperature,
                    feelsLikeTemperature.eveningTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmGridItem.Item(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_evening_title),
                            icon = null,
                            value = OwmGridItem.Value.TextsWithFormat(
                                temp.displayValueWithShortUnit,
                                feelsLikeTemp.displayValueWithShortUnit,
                                formatResId = formatResId
                            )
                        )
                    )
                }

                owmNullSafe(
                    temperature.nightTemperature,
                    feelsLikeTemperature.nightTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmGridItem.Item(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_night_title),
                            icon = null,
                            value = OwmGridItem.Value.TextsWithFormat(
                                temp.displayValueWithShortUnit,
                                feelsLikeTemp.displayValueWithShortUnit,
                                formatResId = formatResId
                            )
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