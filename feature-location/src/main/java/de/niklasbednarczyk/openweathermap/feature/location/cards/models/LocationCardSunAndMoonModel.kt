package de.niklasbednarczyk.openweathermap.feature.location.cards.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueIconModel.Companion.toValueIcon
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridItem
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueItem
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.data.onecall.values.datetime.TimezoneOffsetValue
import de.niklasbednarczyk.openweathermap.feature.location.extensions.displayText
import de.niklasbednarczyk.openweathermap.feature.location.extensions.icon

data class LocationCardSunAndMoonModel(
    override val cardTitle: OwmString?,
    val sunItems: List<OwmGridItem?>,
    val moonItems: List<OwmGridItem?>
) : LocationCardItem {

    companion object {


        fun from(
            timeFormat: OwmTimeFormatType,
            timezoneOffset: TimezoneOffsetValue?,
            dailyForecast: DailyForecastModelData?
        ): LocationCardSunAndMoonModel? {
            val cardTitle =
                OwmString.Resource(R.string.screen_location_card_sun_and_moon_title)

            if (dailyForecast == null) return null

            val sunItems = mutableListOf<OwmGridItem?>()

            sunItems.add(
                owmNullSafe(dailyForecast.sunrise) { sunrise ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_sun_and_moon_value_sunrise),
                        valueIcon = OwmIcons.Sunrise.toValueIcon(),
                        value = OwmValueItem.Texts(
                            sunrise.getTimeString(timezoneOffset, timeFormat)
                        )
                    )
                }
            )

            sunItems.add(
                owmNullSafe(dailyForecast.daylight) { daylight ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_sun_and_moon_value_daylight),
                        valueIcon = OwmIcons.Daylight.toValueIcon(),
                        value = OwmValueItem.Texts(
                            daylight
                        )
                    )
                }
            )

            sunItems.add(
                owmNullSafe(dailyForecast.sunset) { sunset ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_sun_and_moon_value_sunset),
                        valueIcon = OwmIcons.Sunset.toValueIcon(),
                        value = OwmValueItem.Texts(
                            sunset.getTimeString(timezoneOffset, timeFormat)
                        )
                    )
                }
            )

            val moonItems = mutableListOf<OwmGridItem?>()

            moonItems.add(
                owmNullSafe(dailyForecast.moonrise) { sunrise ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_sun_and_moon_value_moonrise),
                        valueIcon = OwmIcons.Sunrise.toValueIcon(),
                        value = OwmValueItem.Texts(
                            sunrise.getTimeString(timezoneOffset, timeFormat)
                        )
                    )

                }
            )

            moonItems.add(
                owmNullSafe(dailyForecast.moonPhase?.type) { moonPhaseType ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_sun_and_moon_value_moon_phase),
                        valueIcon = moonPhaseType.icon.toValueIcon(),
                        value = OwmValueItem.Texts(
                            moonPhaseType.displayText
                        )

                    )
                }
            )

            moonItems.add(
                owmNullSafe(dailyForecast.moonset) { moonset ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_card_sun_and_moon_value_moonset),
                        valueIcon = OwmIcons.Sunrise.toValueIcon(),
                        value = OwmValueItem.Texts(
                            moonset.getTimeString(timezoneOffset, timeFormat)
                        )
                    )
                }
            )

            return owmNullSafeList(
                sunItems,
                moonItems
            ) { sItems, mItems ->
                LocationCardSunAndMoonModel(
                    cardTitle = cardTitle,
                    sunItems = sItems,
                    moonItems = mItems
                )
            }
        }

    }

}