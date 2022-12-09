package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridModel
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridValueItem
import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

data class LocationOverviewTodayTemperaturesModel(
    override val cardTitle: OwmString?,
    val thresholdItems: List<OwmListItem<OwmGridModel>>,
    val dayItems: List<OwmListItem<OwmGridModel>>
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

                val thresholdItems = mutableListOf<OwmListItem<OwmGridModel>>()

                owmNullSafe(temperature.minDailyTemperature) { minDailyTemperature ->
                    thresholdItems.add(
                        OwmListItem.Full(
                            OwmGridModel(
                                title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_threshold_min_title),
                                icon = null,
                                value = OwmGridValueItem.Texts(
                                    minDailyTemperature.displayValue,
                                    minDailyTemperature.getUnit(units)
                                )
                            )
                        )
                    )
                }

                owmNullSafe(temperature.maxDailyTemperature) { maxDailyTemperature ->
                    thresholdItems.add(
                        OwmListItem.Full(
                            OwmGridModel(
                                title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_threshold_max_title),
                                icon = null,
                                value = OwmGridValueItem.Texts(
                                    maxDailyTemperature.displayValue,
                                    maxDailyTemperature.getUnit(units)
                                )
                            )
                        )
                    )
                }

                val dayItems = mutableListOf<OwmListItem<OwmGridModel>>()

                val formatResId = R.string.format_temperature_feels_like

                owmNullSafe(
                    temperature.morningTemperature,
                    feelsLikeTemperature.morningTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmListItem.Full(
                            OwmGridModel(
                                title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_morning_title),
                                icon = null,
                                value = OwmGridValueItem.TextsWithFormat(
                                    temp.displayValueWithShortUnit,
                                    feelsLikeTemp.displayValueWithShortUnit,
                                    formatResId = formatResId
                                )
                            )
                        )
                    )
                }

                owmNullSafe(
                    temperature.dayTemperature,
                    feelsLikeTemperature.dayTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmListItem.Full(
                            OwmGridModel(
                                title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_day_title),
                                icon = null,
                                value = OwmGridValueItem.TextsWithFormat(
                                    temp.displayValueWithShortUnit,
                                    feelsLikeTemp.displayValueWithShortUnit,
                                    formatResId = formatResId
                                )
                            )
                        )
                    )
                }

                owmNullSafe(
                    temperature.eveningTemperature,
                    feelsLikeTemperature.eveningTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmListItem.Full(
                            OwmGridModel(
                                title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_evening_title),
                                icon = null,
                                value = OwmGridValueItem.TextsWithFormat(
                                    temp.displayValueWithShortUnit,
                                    feelsLikeTemp.displayValueWithShortUnit,
                                    formatResId = formatResId
                                )
                            )
                        )
                    )
                }

                owmNullSafe(
                    temperature.nightTemperature,
                    feelsLikeTemperature.nightTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmListItem.Full(
                            OwmGridModel(
                                title = OwmString.Resource(R.string.screen_location_overview_today_temperatures_day_night_title),
                                icon = null,
                                value = OwmGridValueItem.TextsWithFormat(
                                    temp.displayValueWithShortUnit,
                                    feelsLikeTemp.displayValueWithShortUnit,
                                    formatResId = formatResId
                                )
                            )
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