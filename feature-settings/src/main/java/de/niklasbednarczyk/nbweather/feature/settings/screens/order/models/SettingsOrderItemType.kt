package de.niklasbednarczyk.nbweather.feature.settings.screens.order.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R

enum class SettingsOrderItemType {

    CURRENT_WEATHER,
    DAILY,
    HOURLY,
    PRECIPITATION,
    SUN_AND_MOON;

    val title: NBString
        get() {
            val resId = when (this) {
                CURRENT_WEATHER -> R.string.screen_forecast_overview_current_weather_title
                DAILY -> R.string.screen_forecast_overview_daily_title
                HOURLY -> R.string.screen_forecast_overview_hourly_title
                PRECIPITATION -> R.string.screen_forecast_overview_precipitation_title
                SUN_AND_MOON -> R.string.screen_forecast_overview_sun_and_moon_title
            }
            return NBString.ResString(resId)
        }

    private fun getSortOrder(order: NBOrderModel): Int {
        return when (this) {
            CURRENT_WEATHER -> order.currentWeatherOrder
            DAILY -> order.dailyOrder
            HOURLY -> order.hourlyOrder
            PRECIPITATION -> order.precipitationOrder
            SUN_AND_MOON -> order.sunAndMoonOrder
        }
    }

    companion object {

        fun from(
            order: NBOrderModel
        ): List<SettingsOrderItemType> {
            return entries.sortedBy { type ->
                type.getSortOrder(order)
            }
        }

        fun List<SettingsOrderItemType>.toOrder(): NBOrderModel? {
            return nbNullSafe(
                orderOfType(CURRENT_WEATHER),
                orderOfType(DAILY),
                orderOfType(HOURLY),
                orderOfType(PRECIPITATION),
                orderOfType(SUN_AND_MOON)
            ) { currentWeatherOrder, dailyOrder, hourlyOrder, precipitationOrder, sunAndMoonOrder ->
                NBOrderModel(
                    currentWeatherOrder = currentWeatherOrder,
                    dailyOrder = dailyOrder,
                    hourlyOrder = hourlyOrder,
                    precipitationOrder = precipitationOrder,
                    sunAndMoonOrder = sunAndMoonOrder
                )
            }
        }

        private fun List<SettingsOrderItemType>.orderOfType(type: SettingsOrderItemType): Int? {
            val index = indexOf(type)
            return if (index < 0) null else index + 1
        }

    }

}