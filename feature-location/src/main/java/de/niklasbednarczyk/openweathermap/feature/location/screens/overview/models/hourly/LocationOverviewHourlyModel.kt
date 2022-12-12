package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.hourly

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridItem
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueIconModel.Companion.toValueIcon
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueItem
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.icon
import de.niklasbednarczyk.openweathermap.feature.location.extensions.probabilityOfPrecipitationValue
import de.niklasbednarczyk.openweathermap.feature.location.extensions.temperatureWithFeelsLikeValue

data class LocationOverviewHourlyModel(
    val forecastTime: Long,
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
                                    value = OwmValueItem.Texts(
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
                                    value = OwmValueItem.Icon(
                                        valueIcon = weatherIcon.type.icon.toValueIcon()
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
                                    value = temperatureWithFeelsLikeValue(
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
                                    value = probabilityOfPrecipitationValue(
                                        probabilityOfPrecipitation
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
                                    value = OwmValueItem.IconWithTexts(
                                        valueIcon = OwmIcons.WindDegrees.toValueIcon(windDegrees.rotationDegrees),
                                        texts = texts.toTypedArray()
                                    )
                                )
                            }
                        )

                        val itemsExpanded = itemsMedium.toMutableList()

                        val rainGridItem =
                            owmNullSafe(hourlyForecast.rain1hVolume) { rain1hVolume ->
                                OwmGridItem.OneLine(
                                    value = OwmValueItem.IconWithTexts(
                                        valueIcon = OwmIcons.Rain.toValueIcon(),
                                        rain1hVolume.displayValue,
                                        rain1hVolume.unit
                                    )
                                )
                            }

                        val snowGridItem =
                            owmNullSafe(hourlyForecast.snow1hVolume) { snow1hVolume ->
                                OwmGridItem.OneLine(
                                    value = OwmValueItem.IconWithTexts(
                                        valueIcon = OwmIcons.Snow.toValueIcon(),
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
