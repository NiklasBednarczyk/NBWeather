package de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.hourly

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueIconModel.Companion.toValueIcon
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.feature.location.extensions.icon
import de.niklasbednarczyk.nbweather.feature.location.extensions.toValueItem
import java.time.DayOfWeek

data class LocationOverviewHourlyModel(
    val forecastTime: NBDateTimeModel?,
    val itemsCompact: List<NBGridItem.OneLine?>,
    val itemsMedium: List<NBGridItem.OneLine?>,
    val itemsExpanded: List<NBGridItem.OneLine?>
) {

    companion object {

        fun from(
            oneCall: OneCallModelData,
        ): Map<DayOfWeek?, List<LocationOverviewHourlyModel>> {

            return oneCall.hourlyForecasts
                .groupBy { hourly ->
                    hourly.forecastTime?.dateDayOfWeekType
                }.mapValues { entry ->
                    entry.value.mapNotNull { hourlyForecast ->
                        val itemsCompact = mutableListOf<NBGridItem.OneLine?>()

                        itemsCompact.add(
                            nbNullSafe(
                                hourlyForecast.forecastTime
                            ) { time ->
                                NBGridItem.OneLine(
                                    value = NBValueItem.Time(time)
                                )
                            }
                        )

                        itemsCompact.add(
                            nbNullSafe(
                                hourlyForecast.weather?.icon
                            ) { weatherIcon ->
                                NBGridItem.OneLine(
                                    value = NBValueItem.Icon(
                                        valueIcon = weatherIcon.icon.toValueIcon()
                                    )
                                )
                            }
                        )

                        itemsCompact.add(
                            nbNullSafe(hourlyForecast.temperature) { temperature ->
                                NBGridItem.OneLine(
                                    value = NBValueItem.Units(
                                        unitsValue = temperature.getShort()
                                    )
                                )
                            }
                        )

                        itemsCompact.add(
                            nbNullSafe(
                                hourlyForecast.probabilityOfPrecipitation
                            ) { probabilityOfPrecipitation ->
                                NBGridItem.OneLine(
                                    value = probabilityOfPrecipitation.toValueItem()
                                )
                            }
                        )

                        val itemsMedium = itemsCompact.toMutableList()

                        itemsMedium.add(
                            nbNullSafe(
                                hourlyForecast.windDegrees,
                                hourlyForecast.windSpeed
                            ) { windDegrees, windSpeed ->

                                NBGridItem.OneLine(
                                    value = NBValueItem.IconWithUnits(
                                        valueIcon = NBIcons.WindDegrees.toValueIcon(windDegrees.rotationDegrees),
                                        unitsValue = windSpeed
                                    )
                                )
                            }
                        )

                        val itemsExpanded = itemsMedium.toMutableList()

                        val rainGridItem =
                            nbNullSafe(hourlyForecast.rain1hVolume) { rain1hVolume ->
                                NBGridItem.OneLine(
                                    value = NBValueItem.IconWithUnits(
                                        valueIcon = NBIcons.Rain.toValueIcon(),
                                        unitsValue = rain1hVolume,
                                    )
                                )
                            }

                        val snowGridItem =
                            nbNullSafe(hourlyForecast.snow1hVolume) { snow1hVolume ->
                                NBGridItem.OneLine(
                                    value = NBValueItem.IconWithUnits(
                                        valueIcon = NBIcons.Snow.toValueIcon(),
                                        unitsValue = snow1hVolume,
                                    )
                                )
                            }

                        itemsExpanded.add(
                            when {
                                rainGridItem == null && snowGridItem == null -> null
                                rainGridItem == null -> snowGridItem
                                snowGridItem == null -> rainGridItem
                                else -> {
                                    nbNullSafe(
                                        hourlyForecast.rain1hVolume,
                                        hourlyForecast.snow1hVolume
                                    ) { rain1hVolume, snow1hVolume ->
                                        if (rain1hVolume.value >= snow1hVolume.value) {
                                            rainGridItem
                                        } else {
                                            snowGridItem
                                        }
                                    }
                                }
                            }
                        )

                        nbNullSafe(
                            hourlyForecast.forecastTime,
                        ) { forecastTime ->
                            LocationOverviewHourlyModel(
                                forecastTime = forecastTime,
                                itemsCompact = itemsCompact,
                                itemsMedium = itemsMedium,
                                itemsExpanded = itemsExpanded
                            )
                        }
                    }

                }

        }

    }

}
