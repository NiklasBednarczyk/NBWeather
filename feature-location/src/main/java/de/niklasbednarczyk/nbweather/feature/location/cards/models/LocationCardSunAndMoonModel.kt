package de.niklasbednarczyk.nbweather.feature.location.cards.models

import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueIconModel.Companion.toValueIcon
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.datetime.TimezoneOffsetValue
import de.niklasbednarczyk.nbweather.feature.location.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.location.extensions.icon

data class LocationCardSunAndMoonModel(
    override val cardTitle: NBString?,
    val sunItems: List<NBGridItem?>,
    val moonItems: List<NBGridItem?>
) : LocationCardItem {

    companion object {


        fun from(
            timeFormat: NBTimeFormatType,
            timezoneOffset: TimezoneOffsetValue?,
            dailyForecast: DailyForecastModelData?
        ): LocationCardSunAndMoonModel? {
            val cardTitle =
                NBString.Resource(R.string.screen_location_card_sun_and_moon_title)

            if (dailyForecast == null) return null

            val sunItems = mutableListOf<NBGridItem?>()

            sunItems.add(
                nbNullSafe(dailyForecast.sunrise) { sunrise ->
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_sun_and_moon_value_sunrise),
                        valueIcon = NBIcons.Sunrise.toValueIcon(),
                        value = NBValueItem.Texts(
                            sunrise.getTimeString(timezoneOffset, timeFormat)
                        )
                    )
                }
            )

            sunItems.add(
                nbNullSafe(dailyForecast.daylight) { daylight ->
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_sun_and_moon_value_daylight),
                        valueIcon = NBIcons.Daylight.toValueIcon(),
                        value = NBValueItem.Texts(
                            daylight
                        )
                    )
                }
            )

            sunItems.add(
                nbNullSafe(dailyForecast.sunset) { sunset ->
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_sun_and_moon_value_sunset),
                        valueIcon = NBIcons.Sunset.toValueIcon(),
                        value = NBValueItem.Texts(
                            sunset.getTimeString(timezoneOffset, timeFormat)
                        )
                    )
                }
            )

            val moonItems = mutableListOf<NBGridItem?>()

            moonItems.add(
                nbNullSafe(dailyForecast.moonrise) { moonrise ->
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_sun_and_moon_value_moonrise),
                        valueIcon = NBIcons.Moonrise.toValueIcon(),
                        value = NBValueItem.Texts(
                            moonrise.getTimeString(timezoneOffset, timeFormat)
                        )
                    )

                }
            )

            moonItems.add(
                nbNullSafe(dailyForecast.moonPhase?.type) { moonPhaseType ->
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_sun_and_moon_value_moon_phase),
                        valueIcon = moonPhaseType.icon.toValueIcon(),
                        value = NBValueItem.Texts(
                            moonPhaseType.displayText
                        )

                    )
                }
            )

            moonItems.add(
                nbNullSafe(dailyForecast.moonset) { moonset ->
                    NBGridItem.ThreeLines(
                        title = NBString.Resource(R.string.screen_location_card_sun_and_moon_value_moonset),
                        valueIcon = NBIcons.Moonset.toValueIcon(),
                        value = NBValueItem.Texts(
                            moonset.getTimeString(timezoneOffset, timeFormat)
                        )
                    )
                }
            )

            return nbNullSafeList(
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