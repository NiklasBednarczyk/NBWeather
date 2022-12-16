package de.niklasbednarczyk.nbweather.feature.location.cards.models

import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridItem
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem
import de.niklasbednarczyk.nbweather.data.onecall.models.daily.DailyFeelsLikeTemperatureModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.daily.DailyTemperatureModelData
import de.niklasbednarczyk.nbweather.feature.location.extensions.temperatureWithFeelsLikeValue

data class LocationCardTemperaturesModel(
    override val cardTitle: NBString?,
    val thresholdItems: List<NBGridItem>,
    val dayItems: List<NBGridItem>
) : LocationCardItem {

    companion object {

        fun from(
            units: NBUnitsType,
            temperature: DailyTemperatureModelData?,
            feelsLikeTemperature: DailyFeelsLikeTemperatureModelData?
        ): LocationCardTemperaturesModel? {
            val cardTitle =
                NBString.Resource(R.string.screen_location_card_temperatures_title)

            return nbNullSafe(
                temperature,
                feelsLikeTemperature
            ) { temperatureModel, feelsLikeTemperatureModel ->

                val thresholdItems = mutableListOf<NBGridItem>()

                nbNullSafe(temperatureModel.minDailyTemperature) { minDailyTemperature ->
                    thresholdItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_common_temperatures_min),
                            value = NBValueItem.Texts(
                                minDailyTemperature.displayValue,
                                minDailyTemperature.getUnit(units)
                            )
                        )
                    )
                }

                nbNullSafe(temperatureModel.maxDailyTemperature) { maxDailyTemperature ->
                    thresholdItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_common_temperatures_max),
                            value = NBValueItem.Texts(
                                maxDailyTemperature.displayValue,
                                maxDailyTemperature.getUnit(units)
                            )
                        )
                    )
                }

                val dayItems = mutableListOf<NBGridItem>()

                nbNullSafe(
                    temperatureModel.morningTemperature,
                    feelsLikeTemperatureModel.morningTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_card_temperatures_value_morning),
                            value = temperatureWithFeelsLikeValue(temp, feelsLikeTemp)
                        )
                    )
                }

                nbNullSafe(
                    temperatureModel.dayTemperature,
                    feelsLikeTemperatureModel.dayTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_card_temperatures_value_day),
                            value = temperatureWithFeelsLikeValue(temp, feelsLikeTemp)
                        )
                    )
                }

                nbNullSafe(
                    temperatureModel.eveningTemperature,
                    feelsLikeTemperatureModel.eveningTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_card_temperatures_value_evening),
                            value = temperatureWithFeelsLikeValue(temp, feelsLikeTemp)
                        )
                    )
                }

                nbNullSafe(
                    temperatureModel.nightTemperature,
                    feelsLikeTemperatureModel.nightTemperature
                ) { temp, feelsLikeTemp ->
                    dayItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_card_temperatures_value_night),
                            value = temperatureWithFeelsLikeValue(temp, feelsLikeTemp)
                        )
                    )
                }

                nbNullSafeList(
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