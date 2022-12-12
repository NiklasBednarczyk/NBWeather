package de.niklasbednarczyk.openweathermap.feature.location.cards.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridItem
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueItem
import de.niklasbednarczyk.openweathermap.data.onecall.models.daily.DailyFeelsLikeTemperatureModelData
import de.niklasbednarczyk.openweathermap.data.onecall.models.daily.DailyTemperatureModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.temperatureWithFeelsLikeValue

data class LocationCardTemperaturesModel(
    override val cardTitle: OwmString?,
    val thresholdItems: List<OwmGridItem>,
    val dayItems: List<OwmGridItem>
) : LocationCardItem {

    companion object {

        fun from(
            units: OwmUnitsType,
            temperature: DailyTemperatureModelData?,
            feelsLikeTemperature: DailyFeelsLikeTemperatureModelData?
        ): LocationCardTemperaturesModel? {
            val cardTitle =
                OwmString.Resource(R.string.screen_location_card_temperatures_title)

            return owmNullSafe(
                temperature,
                feelsLikeTemperature
            ) { temperatureModel, feelsLikeTemperatureModel ->

                val thresholdItems = mutableListOf<OwmGridItem>()

                owmNullSafe(temperatureModel.minDailyTemperature) { minDailyTemperature ->
                    thresholdItems.add(
                        OwmGridItem.TwoLines(
                            title = OwmString.Resource(R.string.screen_location_common_temperatures_min),
                            value = OwmValueItem.Texts(
                                minDailyTemperature.displayValue,
                                minDailyTemperature.getUnit(units)
                            )
                        )
                    )
                }

                owmNullSafe(temperatureModel.maxDailyTemperature) { maxDailyTemperature ->
                    thresholdItems.add(
                        OwmGridItem.TwoLines(
                            title = OwmString.Resource(R.string.screen_location_common_temperatures_max),
                            value = OwmValueItem.Texts(
                                maxDailyTemperature.displayValue,
                                maxDailyTemperature.getUnit(units)
                            )
                        )
                    )
                }

                val dayItems = mutableListOf<OwmGridItem>()

                owmNullSafe(
                    temperatureModel.morningTemperature,
                    feelsLikeTemperatureModel.morningTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmGridItem.TwoLines(
                            title = OwmString.Resource(R.string.screen_location_card_temperatures_value_morning),
                            value = temperatureWithFeelsLikeValue(temp, feelsLikeTemp)
                        )
                    )
                }

                owmNullSafe(
                    temperatureModel.dayTemperature,
                    feelsLikeTemperatureModel.dayTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmGridItem.TwoLines(
                            title = OwmString.Resource(R.string.screen_location_card_temperatures_value_day),
                            value = temperatureWithFeelsLikeValue(temp, feelsLikeTemp)
                        )
                    )
                }

                owmNullSafe(
                    temperatureModel.eveningTemperature,
                    feelsLikeTemperatureModel.eveningTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmGridItem.TwoLines(
                            title = OwmString.Resource(R.string.screen_location_card_temperatures_value_evening),
                            value = temperatureWithFeelsLikeValue(temp, feelsLikeTemp)
                        )
                    )
                }

                owmNullSafe(
                    temperatureModel.nightTemperature,
                    feelsLikeTemperatureModel.nightTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        OwmGridItem.TwoLines(
                            title = OwmString.Resource(R.string.screen_location_card_temperatures_value_night),
                            value = temperatureWithFeelsLikeValue(temp, feelsLikeTemp)
                        )
                    )
                }

                owmNullSafeList(
                    thresholdItems,
                    dayItems
                ) { tItems, dItems ->
                    LocationCardTemperaturesModel(
                        cardTitle = cardTitle,
                        thresholdItems = tItems,
                        dayItems = dItems
                    )
                }

            }

        }

    }


}