package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridItem
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.displayText
import de.niklasbednarczyk.openweathermap.feature.location.extensions.icon

data class LocationOverviewTodaySunAndMoonModel(
    val sunItems: List<OwmGridItem>,
    val moonItems: List<OwmGridItem>
) : LocationOverviewTodayItem {

    companion object {


        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType
        ): LocationOverviewTodaySunAndMoonModel? {
            val today = oneCall.today ?: return null
            val timezoneOffset = oneCall.metadata.timezoneOffset

            val sunItems = mutableListOf<OwmGridItem>()

            sunItems.add(
                owmNullSafe(today.sunrise) { sunrise ->
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_sunrise),
                        icon = OwmIcons.Sunrise,
                        value = OwmGridItem.Value.Texts(
                            sunrise.getTimeString(timezoneOffset, timeFormat)
                        )
                    )
                } ?: OwmGridItem.Placeholder
            )

            sunItems.add(
                owmNullSafe(today.daylight) { daylight ->
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_daylight),
                        icon = OwmIcons.Daylight,
                        value = OwmGridItem.Value.Texts(
                            daylight
                        )
                    )
                } ?: OwmGridItem.Placeholder
            )

            sunItems.add(
                owmNullSafe(today.sunset) { sunset ->
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_sunset),
                        icon = OwmIcons.Sunset,
                        value = OwmGridItem.Value.Texts(
                            sunset.getTimeString(timezoneOffset, timeFormat)
                        )
                    )
                } ?: OwmGridItem.Placeholder
            )

            val moonItems = mutableListOf<OwmGridItem>()

            moonItems.add(
                owmNullSafe(today.moonrise) { sunrise ->
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moonrise),
                        icon = OwmIcons.Sunrise,
                        value = OwmGridItem.Value.Texts(
                            sunrise.getTimeString(timezoneOffset, timeFormat)
                        )
                    )
                } ?: OwmGridItem.Placeholder
            )

            moonItems.add(
                owmNullSafe(today.moonPhase?.type) { moonPhaseType ->
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moon_phase),
                        icon = moonPhaseType.icon,
                        value = OwmGridItem.Value.Texts(
                            moonPhaseType.displayText
                        )
                    )
                } ?: OwmGridItem.Placeholder
            )

            moonItems.add(
                owmNullSafe(today.moonset) { moonset ->
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moonset),
                        icon = OwmIcons.Sunrise,
                        value = OwmGridItem.Value.Texts(
                            moonset.getTimeString(timezoneOffset, timeFormat)
                        )
                    )
                } ?: OwmGridItem.Placeholder
            )

            return owmNullSafeList(
                sunItems,
                moonItems
            ) { sItems, mItems ->
                LocationOverviewTodaySunAndMoonModel(
                    sunItems = sItems,
                    moonItems = mItems
                )
            }
        }

    }

}