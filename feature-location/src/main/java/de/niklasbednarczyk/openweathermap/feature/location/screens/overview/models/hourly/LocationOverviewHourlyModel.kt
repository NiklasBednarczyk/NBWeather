package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.hourly

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.card.OwmCardItem
import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem
import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem.Companion.toOwmList
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

data class LocationOverviewHourlyModel(
    override val cardTitle: OwmString?,
    val hours: List<LocationOverviewHourlyHourModel>
) : OwmCardItem {

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType
        ): List<OwmListItem<LocationOverviewHourlyModel>> {
            val hourlyItems = mutableListOf<LocationOverviewHourlyModel>()

            val timezoneOffset = oneCall.metadata.timezoneOffset
            val units = oneCall.metadata.units

            val hourlyForecastGroups = oneCall.hourlyForecasts.groupBy { hourly ->
                hourly.forecastTime?.getDateWeekdayWithDateString(timezoneOffset)
            }

            hourlyForecastGroups.forEach { hourlyForecastGroup ->
                val cardTitle = hourlyForecastGroup.key ?: return@forEach
                val hourlyForecasts = hourlyForecastGroup.value

                val hours = LocationOverviewHourlyHourModel.from(
                    hourlyForecasts,
                    timezoneOffset,
                    timeFormat,
                    units
                )

                owmNullSafeList(hours) { h ->
                    hourlyItems.add(
                        LocationOverviewHourlyModel(
                            cardTitle = cardTitle,
                            hours = h
                        )
                    )
                }
            }

            return hourlyItems.toOwmList()
        }

    }

}
