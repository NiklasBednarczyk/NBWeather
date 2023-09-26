package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue

sealed interface ForecastDailyDayInfoItem {

    data object Divider : ForecastDailyDayInfoItem

    data class Forecasts(
        val forecastValues: List<ForecastValue>
    ) : ForecastDailyDayInfoItem

    data class Summary(
        val forecastTime: NBDateTimeDisplayModel,
        val weatherIcon: WeatherIconType,
        val weatherCondition: WeatherConditionType,
        val minTemperature: TemperatureForecastValue,
        val maxTemperature: TemperatureForecastValue
    ) : ForecastDailyDayInfoItem

    data class SunAndMoon(
        val sunrise: NBDateTimeDisplayModel,
        val sunset: NBDateTimeDisplayModel,
        val moonrise: NBDateTimeDisplayModel,
        val moonset: NBDateTimeDisplayModel,
        val moonPhase: MoonPhaseType
    ) : ForecastDailyDayInfoItem

}