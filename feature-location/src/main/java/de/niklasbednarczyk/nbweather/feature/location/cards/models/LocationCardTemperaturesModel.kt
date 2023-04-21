package de.niklasbednarczyk.nbweather.feature.location.cards.models

import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridItem
import de.niklasbednarczyk.nbweather.data.onecall.models.daily.DailyTemperatureModelData
import de.niklasbednarczyk.nbweather.feature.location.extensions.toValueItemWithUnit

data class LocationCardTemperaturesModel(
    override val cardTitle: NBString?,
    val thresholdItems: List<NBGridItem>,
    val dayItems: List<NBGridItem>
) : LocationCardItem {

    companion object {

        fun from(
            units: NBUnitsType,
            temperature: DailyTemperatureModelData?,
        ): LocationCardTemperaturesModel? {
            val cardTitle =
                NBString.Resource(R.string.screen_location_card_temperatures_title)

            return nbNullSafe(temperature) { temperatureModel ->

                val thresholdItems = mutableListOf<NBGridItem>()

                nbNullSafe(temperatureModel.minDailyTemperature) { minDailyTemperature ->
                    thresholdItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_common_temperatures_min),
                            value = minDailyTemperature.toValueItemWithUnit(units)
                        )
                    )
                }

                nbNullSafe(temperatureModel.maxDailyTemperature) { maxDailyTemperature ->
                    thresholdItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_common_temperatures_max),
                            value = maxDailyTemperature.toValueItemWithUnit(units)
                        )
                    )
                }

                val dayItems = mutableListOf<NBGridItem>()

                nbNullSafe(temperatureModel.morningTemperature) { temp ->
                    dayItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_card_temperatures_value_morning),
                            value = temp.toValueItemWithUnit(units)
                        )
                    )
                }

                nbNullSafe(temperatureModel.dayTemperature) { temp ->
                    dayItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_card_temperatures_value_day),
                            value = temp.toValueItemWithUnit(units)
                        )
                    )
                }

                nbNullSafe(temperatureModel.eveningTemperature) { temp ->
                    dayItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_card_temperatures_value_evening),
                            value = temp.toValueItemWithUnit(units)
                        )
                    )
                }

                nbNullSafe(temperatureModel.nightTemperature) { temp ->
                    dayItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_card_temperatures_value_night),
                            value = temp.toValueItemWithUnit(units)
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