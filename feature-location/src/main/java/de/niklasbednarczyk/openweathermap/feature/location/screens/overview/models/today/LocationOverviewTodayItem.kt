package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

sealed interface LocationOverviewTodayItem {

    //TODO (#9) Alert
    class Alert : LocationOverviewTodayItem

    //TODO (#9) Current weather
    class CurrentWeather : LocationOverviewTodayItem

    //TODO (#9) Day temperatures
    class DayTemperatures : LocationOverviewTodayItem

    //TODO (#9) Header
    class Header : LocationOverviewTodayItem

    //TODO (#9) Sun and moon
    class SunAndMoon : LocationOverviewTodayItem

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): List<LocationOverviewTodayItem> {
            val items = mutableListOf<LocationOverviewTodayItem>()

            //TODO (#9) Make items

            return items
        }


    }


}