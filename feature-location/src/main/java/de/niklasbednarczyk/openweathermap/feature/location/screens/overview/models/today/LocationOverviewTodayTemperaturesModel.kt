package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridItem
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridValueItem
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.temperatureWithFeelsLikeGridValue

data class LocationOverviewTodayTemperaturesModel(
    override val cardTitle: OwmString?,
    val thresholdItems: List<OwmGridItem>,
    val dayItems: List<OwmGridItem>
) : LocationOverviewTodayItem {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): LocationOverviewTodayTemperaturesModel? {
            val cardTitle =
                OwmString.Resource(R.string.screen_location_overview_today_card_temperatures_title)

            val units = oneCall.metadata.units

            return owmNullSafe(
                oneCall.today?.temperature,
                oneCall.today?.feelsLikeTemperature
            ) { temperature, feelsLikeTemperature ->

                val thresholdItems = mutableListOf<OwmGridItem>()

                owmNullSafe(temperature.minDailyTemperature) { minDailyTemperature ->
                    thresholdItems.add(
                        OwmGridItem.TwoLines(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_threshold_min_title),
                            value = OwmGridValueItem.Texts(
                                minDailyTemperature.displayValue,
                                minDailyTemperature.getUnit(units)
                            )
                        )
                    )
                }

                owmNullSafe(temperature.maxDailyTemperature) { maxDailyTemperature ->
                    thresholdItems.add(
                        OwmGridItem.TwoLines(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_threshold_max_title),
                            value = OwmGridValueItem.Texts(
                                maxDailyTemperature.displayValue,
                                maxDailyTemperature.getUnit(units)
                            )
                        )
                    )
                }

                val dayItems = mutableListOf<OwmGridItem>()

                owmNullSafe(
                    temperature.morningTemperature,
                    feelsLikeTemperature.morningTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmGridItem.TwoLines(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_morning_title),
                            value = temperatureWithFeelsLikeGridValue(temp, feelsLikeTemp)
                        )
                    )
                }

                owmNullSafe(
                    temperature.dayTemperature,
                    feelsLikeTemperature.dayTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmGridItem.TwoLines(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_day_title),
                            value = temperatureWithFeelsLikeGridValue(temp, feelsLikeTemp)
                        )
                    )
                }

                owmNullSafe(
                    temperature.eveningTemperature,
                    feelsLikeTemperature.eveningTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmGridItem.TwoLines(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_evening_title),
                            value = temperatureWithFeelsLikeGridValue(temp, feelsLikeTemp)
                        )
                    )
                }

                owmNullSafe(
                    temperature.nightTemperature,
                    feelsLikeTemperature.nightTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmGridItem.TwoLines(
                            title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_night_title),
                            value = temperatureWithFeelsLikeGridValue(temp, feelsLikeTemp)
                        )
                    )
                }

                owmNullSafeList(
                    thresholdItems,
                    dayItems
                ) { tItems, dItems ->
                    LocationOverviewTodayTemperaturesModel(
                        cardTitle = cardTitle,
                        thresholdItems = tItems,
                        dayItems = dItems
                    )
                }

            }

        }

    }


}