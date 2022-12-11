package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridIconModel.Companion.toGridIcon
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridItem
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridValueItem
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
                        gridIcon = OwmIcons.Sunrise.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            sunrise.getTimeString(timezoneOffset, timeFormat)
                        )
                    )
                }
            )

            sunItems.add(
                owmNullSafe(today.daylight) { daylight ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_daylight),
                        gridIcon = OwmIcons.Daylight.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            daylight
                        )
                    )
                }
            )

            sunItems.add(
                owmNullSafe(today.sunset) { sunset ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_sunset),
                        gridIcon = OwmIcons.Sunset.toGridIcon(),
                        value = OwmGridValueItem.Texts(
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
                        gridIcon = OwmIcons.Sunrise.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            sunrise.getTimeString(timezoneOffset, timeFormat)
                        )
                    )

                }
            )

            moonItems.add(
                owmNullSafe(today.moonPhase?.type) { moonPhaseType ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moon_phase),
                        gridIcon = moonPhaseType.icon.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            moonPhaseType.displayText
                        )

                    )
                }
            )

            moonItems.add(
                owmNullSafe(today.moonset) { moonset ->
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moonset),
                        gridIcon = OwmIcons.Sunrise.toGridIcon(),
                        value = OwmGridValueItem.Texts(
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