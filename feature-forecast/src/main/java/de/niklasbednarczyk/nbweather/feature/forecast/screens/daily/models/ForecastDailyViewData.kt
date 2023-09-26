package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerViewData
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
            oneCall: OneCallModelData,
            forecastTime: Long?
        ): ForecastDailyViewData? {
            val timezoneOffset = oneCall.timezoneOffset

            val items = oneCall.dailyForecasts.mapNotNull { dailyForecast ->
                ForecastDailyDayModel.from(
                    dailyForecast = dailyForecast,
                    timezoneOffset = timezoneOffset
                )
            }
            return nbNullSafe(items) { i ->
                ForecastDailyViewData(
                    items = i,
                    initialKey = forecastTime
                )
            }

        }

    }

}