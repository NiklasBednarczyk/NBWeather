package de.niklasbednarczyk.nbweather.feature.location.cards.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridItem
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem
import de.niklasbednarczyk.nbweather.data.onecall.models.daily.DailyTemperatureModelData

data class LocationCardTemperaturesModel(
    override val cardTitle: NBString?,
    val thresholdItems: List<NBGridItem.TwoLines?>,
    val dayItems: List<NBGridItem.TwoLines?>
) : LocationCardItem {

    companion object {

        fun from(
            temperature: DailyTemperatureModelData?,
        ): LocationCardTemperaturesModel? {
            val cardTitle =
                NBString.Resource(R.string.screen_location_card_temperatures_title)

            return nbNullSafe(temperature) { temperatureModel ->

                val thresholdItems = mutableListOf<NBGridItem.TwoLines?>()

                nbNullSafe(temperatureModel.minDailyTemperature) { minDailyTemperature ->
                    thresholdItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_common_temperatures_min),
                            value = NBValueItem.Units(minDailyTemperature.getLong())
                        )
                    )
                }

                nbNullSafe(temperatureModel.maxDailyTemperature) { maxDailyTemperature ->
                    thresholdItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_common_temperatures_max),
                            value = NBValueItem.Units(maxDailyTemperature.getLong())
                        )
                    )
                }

                val dayItems = mutableListOf<NBGridItem.TwoLines?>()

                nbNullSafe(temperatureModel.morningTemperature) { temp ->
                    dayItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_card_temperatures_value_morning),
                            value = NBValueItem.Units(temp.getLong())
                        )
                    )
                }

                nbNullSafe(temperatureModel.dayTemperature) { temp ->
                    dayItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_card_temperatures_value_day),
                            value = NBValueItem.Units(temp.getLong())
                        )
                    )
                }

                nbNullSafe(temperatureModel.eveningTemperature) { temp ->
                    dayItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_card_temperatures_value_evening),
                            value = NBValueItem.Units(temp.getLong())
                        )
                    )
                }

                nbNullSafe(temperatureModel.nightTemperature) { temp ->
                    dayItems.add(
                        NBGridItem.TwoLines(
                            title = NBString.Resource(R.string.screen_location_card_temperatures_value_night),
                            value = NBValueItem.Units(temp.getLong())
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