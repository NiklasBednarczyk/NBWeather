package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.displayText
import de.niklasbednarczyk.openweathermap.feature.location.extensions.icon
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.sunandmoon.LocationOverviewTodaySunAndMoonItem

data class LocationOverviewTodaySunAndMoonModel(
    val sunItems: List<LocationOverviewTodaySunAndMoonItem>,
    val moonItems: List<LocationOverviewTodaySunAndMoonItem>
) : LocationOverviewTodayItem {

    companion object {


        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType
        ): LocationOverviewTodaySunAndMoonModel? {
            val today = oneCall.today ?: return null
            val timezoneOffset = oneCall.metadata.timezoneOffset

            val sunItems = mutableListOf<LocationOverviewTodaySunAndMoonItem>()

            sunItems.add(
                owmNullSafe(today.sunrise) { sunrise ->
                    LocationOverviewTodaySunAndMoonItem.Icon(
                        icon = OwmIcons.Sunrise,
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_sunrise),
                        value = sunrise.getTimeString(timezoneOffset, timeFormat)
                    )
                } ?: LocationOverviewTodaySunAndMoonItem.Placeholder
            )

            sunItems.add(
                owmNullSafe(today.sunTime) { sunTime ->
                    LocationOverviewTodaySunAndMoonItem.Icon(
                        icon = OwmIcons.SunTime,
                        title = OwmString.Resource(R.string.icon_content_description_sun_time),
                        value = sunTime
                    )
                } ?: LocationOverviewTodaySunAndMoonItem.Placeholder
            )

            sunItems.add(
                owmNullSafe(today.sunset) { sunset ->
                    LocationOverviewTodaySunAndMoonItem.Icon(
                        icon = OwmIcons.Sunset,
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_sunset),
                        value = sunset.getTimeString(timezoneOffset, timeFormat)
                    )
                } ?: LocationOverviewTodaySunAndMoonItem.Placeholder
            )

            val moonItems = mutableListOf<LocationOverviewTodaySunAndMoonItem>()

            moonItems.add(
                owmNullSafe(today.moonrise) { moonrise ->
                    LocationOverviewTodaySunAndMoonItem.Icon(
                        icon = OwmIcons.Moonrise,
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moonrise),
                        value = moonrise.getTimeString(timezoneOffset, timeFormat)
                    )
                } ?: LocationOverviewTodaySunAndMoonItem.Placeholder
            )

            moonItems.add(
                owmNullSafe(today.moonPhase?.type) { moonPhaseType ->
                    LocationOverviewTodaySunAndMoonItem.Icon(
                        icon = moonPhaseType.icon,
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moon_phase),
                        value = moonPhaseType.displayText
                    )
                } ?: LocationOverviewTodaySunAndMoonItem.Placeholder
            )

            moonItems.add(
                owmNullSafe(today.moonset) { moonset ->
                    LocationOverviewTodaySunAndMoonItem.Icon(
                        icon = OwmIcons.Moonset,
                        title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moonset),
                        value = moonset.getTimeString(timezoneOffset, timeFormat)
                    )
                } ?: LocationOverviewTodaySunAndMoonItem.Placeholder
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