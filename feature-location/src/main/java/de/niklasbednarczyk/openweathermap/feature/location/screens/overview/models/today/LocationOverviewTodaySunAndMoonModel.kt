package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridModel
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridValueItem
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.displayText
import de.niklasbednarczyk.openweathermap.feature.location.extensions.icon

data class LocationOverviewTodaySunAndMoonModel(
    override val cardTitle: OwmString?,
    val sunItems: List<OwmListItem<OwmGridModel>>,
    val moonItems: List<OwmListItem<OwmGridModel>>
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

            val sunItems = mutableListOf<OwmListItem<OwmGridModel>>()

            sunItems.add(
                owmNullSafe(today.sunrise) { sunrise ->
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_sunrise),
                            icon = OwmIcons.Sunrise,
                            value = OwmGridValueItem.Texts(
                                sunrise.getTimeString(timezoneOffset, timeFormat)
                            )
                        )
                    )
                } ?: OwmListItem.Empty
            )

            sunItems.add(
                owmNullSafe(today.daylight) { daylight ->
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_daylight),
                            icon = OwmIcons.Daylight,
                            value = OwmGridValueItem.Texts(
                                daylight
                            )
                        )
                    )
                } ?: OwmListItem.Empty
            )

            sunItems.add(
                owmNullSafe(today.sunset) { sunset ->
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_sunset),
                            icon = OwmIcons.Sunset,
                            value = OwmGridValueItem.Texts(
                                sunset.getTimeString(timezoneOffset, timeFormat)
                            )
                        )
                    )
                } ?: OwmListItem.Empty
            )

            val moonItems = mutableListOf<OwmListItem<OwmGridModel>>()

            moonItems.add(
                owmNullSafe(today.moonrise) { sunrise ->
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moonrise),
                            icon = OwmIcons.Sunrise,
                            value = OwmGridValueItem.Texts(
                                sunrise.getTimeString(timezoneOffset, timeFormat)
                            )
                        )
                    )
                } ?: OwmListItem.Empty
            )

            moonItems.add(
                owmNullSafe(today.moonPhase?.type) { moonPhaseType ->
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moon_phase),
                            icon = moonPhaseType.icon,
                            value = OwmGridValueItem.Texts(
                                moonPhaseType.displayText
                            )
                        )
                    )
                } ?: OwmListItem.Empty
            )

            moonItems.add(
                owmNullSafe(today.moonset) { moonset ->
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_sun_and_moon_moonset),
                            icon = OwmIcons.Sunrise,
                            value = OwmGridValueItem.Texts(
                                moonset.getTimeString(timezoneOffset, timeFormat)
                            )
                        )
                    )
                } ?: OwmListItem.Empty
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