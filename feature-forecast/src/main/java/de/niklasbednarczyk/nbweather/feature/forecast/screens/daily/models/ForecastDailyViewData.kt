package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerViewData
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData

data class ForecastDailyViewData(
    override val items: List<ForecastDailyDayModel>,
    override val initialKey: Long?
) : NBPagerViewData<Long, ForecastDailyDayModel> {

    override fun getItemKey(item: ForecastDailyDayModel): Long {
        return item.dateTime.value
    }

    companion object {

        fun from(
            forecastTime: Long?,
            oneCall: OneCallModelData
        ): ForecastDailyViewData? {
            return from(
                forecastTime = forecastTime,
                timezoneOffset = oneCall.timezoneOffset,
                dailyForecasts = oneCall.dailyForecasts
            )
        }

        fun from(
            forecastTime: Long?,
            timezoneOffset: NBTimezoneOffsetValue?,
            dailyForecasts: List<DailyForecastModelData>
        ): ForecastDailyViewData? {
            val items = dailyForecasts.mapNotNull { dailyForecast ->
                ForecastDailyDayModel.from(
                    dailyForecast = dailyForecast,
                    timezoneOffset = timezoneOffset
                )
            }
            return nbNullSafeList(items) { i ->
                ForecastDailyViewData(
                    items = i,
                    initialKey = forecastTime
                )
            }

        }

    }

}