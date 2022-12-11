package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.hourly

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridIconModel.Companion.toGridIcon
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridItem
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridValueItem
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.icon
import de.niklasbednarczyk.openweathermap.feature.location.extensions.temperatureWithFeelsLikeGridValue

data class LocationOverviewHourlyModel(
//    override val cardTitle: OwmString?,
//    val hours: List<LocationOverviewHourlyHourModel>
    val forecastTimeValue: Long,
    val itemsCompact: List<OwmGridItem?>,
    val itemsMedium: List<OwmGridItem?>,
    val itemsExpanded: List<OwmGridItem?>
) {

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType
        ): Map<OwmString?, List<LocationOverviewHourlyModel>> {
            val timezoneOffset = oneCall.metadata.timezoneOffset
            val units = oneCall.metadata.units

            return oneCall.hourlyForecasts
                .groupBy { hourly ->
                    hourly.forecastTime?.getDateWeekdayWithDateString(timezoneOffset)
                }.mapValues { entry ->
                    entry.value.mapNotNull { hourlyForecast ->
                        val itemsCompact = mutableListOf<OwmGridItem?>()

                        itemsCompact.add(
                            owmNullSafe(
                                hourlyForecast.forecastTime?.getTimeString(
                                    timezoneOffset,
                                    timeFormat,
                                    false
                                )
                            ) { time ->
                                OwmGridItem.OneLine(
                                    value = OwmGridValueItem.Texts(
                                        time
                                    )
                                )
                            }
                        )

                        itemsCompact.add(
                            owmNullSafe(
                                hourlyForecast.weather?.icon
                            ) { weatherIcon ->
                                OwmGridItem.OneLine(
                                    value = OwmGridValueItem.Icon(
                                        gridIcon = weatherIcon.type.icon.toGridIcon()
                                    )
                                )
                            }
                        )

                        itemsCompact.add(
                            owmNullSafe(
                                hourlyForecast.temperature,
                                hourlyForecast.feelsLikeTemperature
                            ) { temperature, feelsLikeTemperature ->
                                OwmGridItem.OneLine(
                                    value = temperatureWithFeelsLikeGridValue(
                                        temperature,
                                        feelsLikeTemperature
                                    )
                                )
                            }
                        )

                        itemsCompact.add(
                            owmNullSafe(
                                hourlyForecast.probabilityOfPrecipitation
                            ) { probabilityOfPrecipitation ->
                                OwmGridItem.OneLine(
                                    value = OwmGridValueItem.IconWithTexts(
                                        gridIcon = OwmIcons.ProbabilityOfPrecipitation.toGridIcon(),
                                        probabilityOfPrecipitation.displayValue,
                                        probabilityOfPrecipitation.unit
                                    )
                                )
                            }
                        )

                        val itemsMedium = itemsCompact.toMutableList()

                        itemsMedium.add(
                            owmNullSafe(
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

                                OwmGridItem.OneLine(
                                    value = OwmGridValueItem.IconWithTexts(
                                        gridIcon = OwmIcons.WindDegrees.toGridIcon(windDegrees.rotationDegrees),
                                        texts = texts.toTypedArray()
                                    )
                                )
                            }
                        )

                        val itemsExpanded = itemsMedium.toMutableList()

                        val rainGridItem =
                            owmNullSafe(hourlyForecast.rain1hVolume) { rain1hVolume ->
                                OwmGridItem.OneLine(
                                    value = OwmGridValueItem.IconWithTexts(
                                        gridIcon = OwmIcons.Rain.toGridIcon(),
                                        rain1hVolume.displayValue,
                                        rain1hVolume.unit
                                    )
                                )
                            }

                        val snowGridItem =
                            owmNullSafe(hourlyForecast.snow1hVolume) { snow1hVolume ->
                                OwmGridItem.OneLine(
                                    value = OwmGridValueItem.IconWithTexts(
                                        gridIcon = OwmIcons.Snow.toGridIcon(),
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
                                    owmNullSafe(
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

                        owmNullSafe(
                            hourlyForecast.forecastTime?.value,
                        ) { forecastTime ->
                            LocationOverviewHourlyModel(
                                forecastTimeValue = forecastTime,
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
