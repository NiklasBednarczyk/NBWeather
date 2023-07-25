package de.niklasbednarczyk.nbweather.feature.location.screens.daily.models

import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerViewData
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData

data class LocationDailyViewData(
    override val items: List<LocationDailyDayModel>,
    override val initialKey: Long?
) : NBPagerViewData<LocationDailyDayModel, Long?> {

    override fun isInitialItem(item: LocationDailyDayModel): Boolean {
        return item.forecastTime?.value == initialKey
    }

    companion object {

        fun from(
            oneCall: OneCallModelData,
            forecastTime: Long?
        ): LocationDailyViewData {
            val dailyForecasts = oneCall.dailyForecasts

            val items = dailyForecasts.mapNotNull { dailyForecast ->
                LocationDailyDayModel.from(
                    dailyForecast,
                )
            }

            return LocationDailyViewData(
                initialKey = forecastTime,
                items = items
            )
        }

    }

}