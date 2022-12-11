package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueIconModel.Companion.toValueIcon
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridItem
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueItem
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.displayText
import de.niklasbednarczyk.openweathermap.feature.location.extensions.icon

data class LocationOverviewTodaySunAndMoonModel(
    override val cardTitle: OwmString?,
    val sunItems: List<OwmGridItem?>,
    val moonItems: List<OwmGridItem?>
) : LocationOverviewTodayItem {

    companion object {


        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType
        ): LocationOverviewTodaySunAndMoonModel? {
            val cardTitle =
                OwmString.Resource(R.string.screen_location_overview_today_card_sun_and_moon_title)

            val today = oneCall.today ?: return null
            val timezoneOffset = oneCall.metadata.timezoneOffset

            val sunItems = mutableListOf<OwmGridItem?>()

            sunItems.add(
                owmNullSafe(today.sunrise) { sunrise ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_sunrise),
                        valueIcon = OwmIcons.Sunrise.toValueIcon(),
                        value = OwmValueItem.Texts(
                            sunrise.getTimeString(timezoneOffset, timeFormat)
                        )
                    )
                }
            )

            sunItems.add(
                owmNullSafe(today.daylight) { daylight ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_daylight),
                        valueIcon = OwmIcons.Daylight.toValueIcon(),
                        value = OwmValueItem.Texts(
                            daylight
                        )
                    )
                }
            )

            sunItems.add(
                owmNullSafe(today.sunset) { sunset ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_sunset),
                        valueIcon = OwmIcons.Sunset.toValueIcon(),
                        value = OwmValueItem.Texts(
                            sunset.getTimeString(timezoneOffset, timeFormat)
                        )
                    )
                }
            )

            val moonItems = mutableListOf<OwmGridItem?>()

            moonItems.add(
                owmNullSafe(today.moonrise) { sunrise ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moonrise),
                        valueIcon = OwmIcons.Sunrise.toValueIcon(),
                        value = OwmValueItem.Texts(
                            sunrise.getTimeString(timezoneOffset, timeFormat)
                        )
                    )

                }
            )

            moonItems.add(
                owmNullSafe(today.moonPhase?.type) { moonPhaseType ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moon_phase),
                        valueIcon = moonPhaseType.icon.toValueIcon(),
                        value = OwmValueItem.Texts(
                            moonPhaseType.displayText
                        )

                    )
                }
            )

            moonItems.add(
                owmNullSafe(today.moonset) { moonset ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moonset),
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
                LocationOverviewTodaySunAndMoonModel(
                    cardTitle = cardTitle,
                    sunItems = sItems,
                    moonItems = mItems
                )
            }
        }

    }

}