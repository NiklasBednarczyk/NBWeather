package de.niklasbednarczyk.nbweather.feature.forecast.models.sunandmoon

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType

sealed interface SunAndMoonItem {

    data class MoonPhase(
        val moonPhase: MoonPhaseType
    ) : SunAndMoonItem

    data class MoonTimes(
        val moonrise: NBDateTimeDisplayModel,
        val moonset: NBDateTimeDisplayModel
    ) : SunAndMoonItem

    data class SunTimes(
        val sunrise: NBDateTimeDisplayModel,
        val sunset: NBDateTimeDisplayModel
    ) : SunAndMoonItem {

        fun calcSunArcPercentage(
            currentTime: NBDateTimeDisplayModel
        ): Float {
            val current = currentTime.dt.value
            val min = sunrise.dt.value
            val max = sunset.dt.value

            val spanMinMax = max - min
            val spanCurrent = current - min
            return spanCurrent.toFloat() / spanMinMax
        }

    }

    companion object {

        fun from(
            timezoneOffset: NBTimezoneOffsetValue?,
            dailyForecast: DailyForecastModelData?
        ): List<SunAndMoonItem>? {
            val sunAndMoonItems = mutableListOf<SunAndMoonItem>()

            nbNullSafe(
                NBDateTimeDisplayModel.from(dailyForecast?.sunrise, timezoneOffset),
                NBDateTimeDisplayModel.from(dailyForecast?.sunset, timezoneOffset),
            ) { sunrise, sunset ->
                if (sunrise.dt.value < sunset.dt.value) {
                    sunAndMoonItems.add(
                        SunTimes(
                            sunrise = sunrise,
                            sunset = sunset
                        )
                    )
                }
            }

            nbNullSafe(
                NBDateTimeDisplayModel.from(dailyForecast?.moonrise, timezoneOffset),
                NBDateTimeDisplayModel.from(dailyForecast?.moonset, timezoneOffset),
            ) { moonrise, moonset ->
                sunAndMoonItems.add(
                    MoonTimes(
                        moonrise = moonrise,
                        moonset = moonset
                    )
                )
            }

            nbNullSafe(dailyForecast?.moonPhase) { moonPhase ->
                sunAndMoonItems.add(
                    MoonPhase(
                        moonPhase = moonPhase
                    )
                )
            }

            return nbNullSafeList(sunAndMoonItems) { items ->
                items
            }
        }

    }

}