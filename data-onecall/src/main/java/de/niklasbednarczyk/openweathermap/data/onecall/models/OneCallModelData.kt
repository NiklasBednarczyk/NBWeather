package de.niklasbednarczyk.openweathermap.data.onecall.models

data class OneCallModelData(
    val metadata: OneCallMetadataModelData,
    val currentWeather: CurrentWeatherModelData,
    val minutelyForecasts: List<MinutelyForecastModelData>,
    val hourlyForecasts: List<HourlyForecastModelData>,
    val dailyForecasts: List<DailyForecastModelData>,
    val nationalWeatherAlerts: List<NationalWeatherAlertModelData>
)