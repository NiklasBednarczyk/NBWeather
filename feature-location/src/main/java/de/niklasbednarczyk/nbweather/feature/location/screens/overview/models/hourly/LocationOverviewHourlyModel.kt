package de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.hourly

import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueIconModel.Companion.toValueIcon
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.feature.location.extensions.icon
import de.niklasbednarczyk.nbweather.feature.location.extensions.toValueItemWithUnit
import de.niklasbednarczyk.nbweather.feature.location.extensions.toValueItem

data class LocationOverviewHourlyModel(
    val forecastTime: Long,
    val itemsCompact: List<NBGridItem?>,
    val itemsMedium: List<NBGridItem?>,
    val itemsExpanded: List<NBGridItem?>
) {

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: NBTimeFormatType
        ): Map<NBString?, List<LocationOverviewHourlyModel>> {
            val timezoneOffset = oneCall.metadata.timezoneOffset
            val units = oneCall.metadata.units

            return oneCall.hourlyForecasts
                .groupBy { hourly ->
                    hourly.forecastTime?.getDateWeekdayWithDateString(timezoneOffset)
                }.mapValues { entry ->
                    entry.value.mapNotNull { hourlyForecast ->
                        val itemsCompact = mutableListOf<NBGridItem?>()

                        itemsCompact.add(
                            nbNullSafe(
                                hourlyForecast.forecastTime?.getTimeString(
                                    timezoneOffset,
                                    timeFormat,
                                    false
                                )
                            ) { time ->
                                NBGridItem.OneLine(
                                    value = NBValueItem.Texts(
                                        time
                                    )
                                )
                            }
                        )

                        itemsCompact.add(
                            nbNullSafe(
                                hourlyForecast.weather?.icon
                            ) { weatherIcon ->
                                NBGridItem.OneLine(
                                    value = NBValueItem.Icon(
                                        valueIcon = weatherIcon.type.icon.toValueIcon()
                                    )
                                )
                            }
                        )

                        itemsCompact.add(
                            nbNullSafe(hourlyForecast.temperature) { temperature ->
                                NBGridItem.OneLine(
                                    value = temperature.toValueItemWithUnit(units)
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

                                val windGust = hourlyForecast.windGust

                                val texts =
                                    if (windGust != null && windGust.value > windSpeed.value) {
                                        listOf(
                                            windGust.displayValue,
                                            windGust.getUnit(units)
                                        )
                                    } else {
                                        listOf(
                                            windSpeed.displayValue,
                                            windSpeed.getUnit(units)
                                        )
                                    }

                                NBGridItem.OneLine(
                                    value = NBValueItem.IconWithTexts(
                                        valueIcon = NBIcons.WindDegrees.toValueIcon(windDegrees.rotationDegrees),
                                        texts = texts.toTypedArray()
                                    )
                                )
                            }
                        )

                        val itemsExpanded = itemsMedium.toMutableList()

                        val rainGridItem =
                            nbNullSafe(hourlyForecast.rain1hVolume) { rain1hVolume ->
                                NBGridItem.OneLine(
                                    value = NBValueItem.IconWithTexts(
                                        valueIcon = NBIcons.Rain.toValueIcon(),
                                        rain1hVolume.displayValue,
                                        rain1hVolume.unit
                                    )
                                )
                            }

                        val snowGridItem =
                            nbNullSafe(hourlyForecast.snow1hVolume) { snow1hVolume ->
                                NBGridItem.OneLine(
                                    value = NBValueItem.IconWithTexts(
                                        valueIcon = NBIcons.Snow.toValueIcon(),
                                        snow1hVolume.displayValue,
                                        snow1hVolume.unit
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
                            hourlyForecast.forecastTime?.value,
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
