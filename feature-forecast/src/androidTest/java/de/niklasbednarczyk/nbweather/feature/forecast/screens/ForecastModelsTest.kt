package de.niklasbednarczyk.nbweather.feature.forecast.screens

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.data.onecall.models.CurrentWeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.HourlyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.MinutelyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.NationalWeatherAlertModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.daily.DailyFeelsLikeTemperatureModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.daily.DailyTemperatureModelData
import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.CloudinessForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.DewPointForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.FeelsLikeForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.HumidityForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.PressureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ProbabilityOfPrecipitationForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.RainForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.SnowForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.UVIndexForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.VisibilityForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindDegreesForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindGustForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindSpeedForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationUnitsValue
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest

interface ForecastModelsTest : NBTest {

    val testTimezoneOffset: NBTimezoneOffsetValue?
        get() = NBTimezoneOffsetValue.from(0L)

    val testForecastTimeValue
        get() = 1672570800L // Sunday, 1 January 2023 11:00:00 (GMT)

    val testStartDateValue
        get() = 1672534861L // Sunday, 1 January 2023 01:01:01 (GMT)

    val testEndDateValue
        get() = 1675303322L // Thursday, 2 February 2023 02:02:02 (GMT)

    fun Long.addDays(days: Int): Long {
        return this + days * 86400
    }

    fun Long.addHours(hours: Int): Long {
        return this + hours * 3600
    }

    fun Long.addMinutes(minutes: Int): Long {
        return this + minutes * 60
    }

    fun createNBString(value: String?) = NBString.Value.from(value)

    fun createTestCurrentWeather(
        currentTimeValue: Long? = testForecastTimeValue,
        sunriseValue: Long? = testStartDateValue,
        sunsetValue: Long? = testEndDateValue,
        currentTemperatureValue: Double? = 1.0,
        feelsLikeTemperatureValue: Double? = 2.0,
        pressureValue: Long? = 3,
        humidityValue: Long? = 4,
        dewPointTemperatureValue: Double? = 5.0,
        cloudinessValue: Long? = 6,
        uvIndexValue: Double? = 7.0,
        visibilityValue: Long? = 8,
        windSpeedValue: Double? = 9.0,
        windGustValue: Double? = 10.0,
        windDegreesValue: Long? = 11,
        rain1hVolumeValue: Double? = 12.0,
        snow1hVolumeValue: Double? = 13.0,
        weatherIcon: WeatherIconType? = WeatherIconType.D_CLEAR_SKY,
        weatherCondition: WeatherConditionType? = WeatherConditionType.CLEAR_SKY
    ): CurrentWeatherModelData {
        return CurrentWeatherModelData(
            currentTime = NBDateTimeValue.from(currentTimeValue),
            sunrise = NBDateTimeValue.from(sunriseValue),
            sunset = NBDateTimeValue.from(sunsetValue),
            currentTemperature = TemperatureForecastValue.from(currentTemperatureValue),
            feelsLikeTemperature = FeelsLikeForecastValue.from(feelsLikeTemperatureValue),
            pressure = PressureForecastValue.from(pressureValue),
            humidity = HumidityForecastValue.from(humidityValue),
            dewPointTemperature = DewPointForecastValue.from(dewPointTemperatureValue),
            cloudiness = CloudinessForecastValue.from(cloudinessValue),
            uvIndex = UVIndexForecastValue.from(uvIndexValue),
            visibility = VisibilityForecastValue.from(visibilityValue),
            windSpeed = WindSpeedForecastValue.from(windSpeedValue),
            windGust = WindGustForecastValue.from(windGustValue),
            windDegrees = WindDegreesForecastValue.from(windDegreesValue),
            rain1hVolume = RainForecastValue.from(rain1hVolumeValue),
            snow1hVolume = SnowForecastValue.from(snow1hVolumeValue),
            weather = WeatherModelData(
                icon = weatherIcon,
                condition = weatherCondition
            )
        )
    }

    fun createTestMinutelyForecasts(
        precipitationValue0: Double,
        precipitationValue1: Double,
        precipitationValue2: Double,
        precipitationValue3: Double
    ): List<MinutelyForecastModelData> {
        return List(60) { index ->
            val precipitationValue = when (index) {
                0 -> precipitationValue0
                1 -> precipitationValue1
                2 -> precipitationValue2
                3 -> precipitationValue3
                else -> 0.0
            }

            MinutelyForecastModelData(
                forecastTime = NBDateTimeValue.from(testForecastTimeValue.addMinutes(index)),
                precipitation = PrecipitationUnitsValue(precipitationValue)
            )
        }
    }

    fun createTestMinutelyForecasts(
        precipitationValue: Double
    ): List<MinutelyForecastModelData> {
        return List(60) { index ->
            MinutelyForecastModelData(
                forecastTime = NBDateTimeValue.from(testForecastTimeValue.addMinutes(index)),
                precipitation = PrecipitationUnitsValue(precipitationValue)
            )
        }
    }

    fun createTestMinutelyForecasts(
        size: Int
    ): List<MinutelyForecastModelData> {
        return List(size) { index ->
            MinutelyForecastModelData(
                forecastTime = NBDateTimeValue.from(testForecastTimeValue.addMinutes(index)),
                precipitation = PrecipitationUnitsValue(index.toDouble())
            )
        }
    }

    fun createTestHourlyForecast(
        forecastTimeValue: Long? = testForecastTimeValue,
        temperatureValue: Double? = 1.0,
        feelsLikeTemperatureValue: Double? = 2.0,
        pressureValue: Long? = 3,
        humidityValue: Long? = 4,
        dewPointTemperatureValue: Double? = 5.0,
        uvIndexValue: Double? = 6.0,
        cloudinessValue: Long? = 7,
        visibilityValue: Long? = 8,
        windSpeedValue: Double? = 9.0,
        windGustValue: Double? = 10.0,
        windDegreesValue: Long? = 11,
        probabilityOfPrecipitationValue: Double? = 12.0,
        rain1hVolumeValue: Double? = 13.0,
        snow1hVolumeValue: Double? = 14.0,
        weatherIcon: WeatherIconType? = WeatherIconType.D_CLEAR_SKY,
        weatherCondition: WeatherConditionType? = WeatherConditionType.CLEAR_SKY
    ): HourlyForecastModelData {
        return HourlyForecastModelData(
            forecastTime = NBDateTimeValue.from(forecastTimeValue),
            temperature = TemperatureForecastValue.from(temperatureValue),
            feelsLikeTemperature = FeelsLikeForecastValue.from(feelsLikeTemperatureValue),
            pressure = PressureForecastValue.from(pressureValue),
            humidity = HumidityForecastValue.from(humidityValue),
            dewPointTemperature = DewPointForecastValue.from(dewPointTemperatureValue),
            uvIndex = UVIndexForecastValue.from(uvIndexValue),
            cloudiness = CloudinessForecastValue.from(cloudinessValue),
            visibility = VisibilityForecastValue.from(visibilityValue),
            windSpeed = WindSpeedForecastValue.from(windSpeedValue),
            windGust = WindGustForecastValue.from(windGustValue),
            windDegrees = WindDegreesForecastValue.from(windDegreesValue),
            probabilityOfPrecipitation = ProbabilityOfPrecipitationForecastValue.from(
                probabilityOfPrecipitationValue
            ),
            rain1hVolume = RainForecastValue.from(rain1hVolumeValue),
            snow1hVolume = SnowForecastValue.from(snow1hVolumeValue),
            weather = WeatherModelData(
                icon = weatherIcon,
                condition = weatherCondition
            )
        )
    }

    fun createTestHourlyForecasts(
        size: Int
    ): List<HourlyForecastModelData> {
        return List(size) { index ->
            HourlyForecastModelData(
                forecastTime = NBDateTimeValue.from(testForecastTimeValue.addDays(index)),
                temperature = TemperatureForecastValue.from(index + 1.0),
                feelsLikeTemperature = FeelsLikeForecastValue.from(index + 2.0),
                pressure = PressureForecastValue.from(index + 3L),
                humidity = HumidityForecastValue.from(index + 4L),
                dewPointTemperature = DewPointForecastValue.from(index + 5.0),
                uvIndex = UVIndexForecastValue.from(index + 6.0),
                cloudiness = CloudinessForecastValue.from(index + 7L),
                visibility = VisibilityForecastValue.from(index + 8L),
                windSpeed = WindSpeedForecastValue.from(index + 9.0),
                windGust = WindGustForecastValue.from(index + 10.0),
                windDegrees = WindDegreesForecastValue.from(index + 11L),
                probabilityOfPrecipitation = ProbabilityOfPrecipitationForecastValue.from(index + 12.0),
                rain1hVolume = RainForecastValue.from(index + 13.0),
                snow1hVolume = SnowForecastValue.from(index + 14.0),
                weather = WeatherModelData(
                    icon = WeatherIconType.values()[index],
                    condition = WeatherConditionType.values()[index]
                )
            )
        }
    }

    fun createTestDailyForecast(
        forecastTimeValue: Long? = testForecastTimeValue,
        sunriseValue: Long? = testStartDateValue,
        sunsetValue: Long? = testEndDateValue,
        moonriseValue: Long? = testStartDateValue.addHours(1),
        moonsetValue: Long? = testEndDateValue.addHours(1),
        moonPhase: MoonPhaseType? = MoonPhaseType.FULL_MOON,
        temperatureMorningValue: Double? = 1.0,
        temperatureDayValue: Double? = 2.0,
        temperatureEveningValue: Double? = 3.0,
        temperatureNightValue: Double? = 4.0,
        temperatureMinDailyValue: Double? = -10.0,
        temperatureMaxDailyValue: Double? = 10.0,
        feelsLikeTemperatureMorningValue: Double? = 1.5,
        feelsLikeTemperatureDayValue: Double? = 2.5,
        feelsLikeTemperatureEveningValue: Double? = 3.5,
        feelsLikeTemperatureNightValue: Double? = 4.5,
        pressureValue: Long? = 5,
        humidityValue: Long? = 6,
        dewPointTemperatureValue: Double? = 7.0,
        windSpeedValue: Double? = 8.0,
        windGustValue: Double? = 9.0,
        windDegreesValue: Long? = 10,
        cloudinessValue: Long? = 11,
        uvIndexValue: Double? = 12.0,
        probabilityOfPrecipitationValue: Double? = 13.0,
        rainVolumeValue: Double? = 14.0,
        snowVolumeValue: Double? = 15.0,
        weatherIcon: WeatherIconType? = WeatherIconType.D_CLEAR_SKY,
        weatherCondition: WeatherConditionType? = WeatherConditionType.CLEAR_SKY
    ): DailyForecastModelData {
        return DailyForecastModelData(
            forecastTime = NBDateTimeValue.from(forecastTimeValue),
            sunrise = NBDateTimeValue.from(sunriseValue),
            sunset = NBDateTimeValue.from(sunsetValue),
            moonrise = NBDateTimeValue.from(moonriseValue),
            moonset = NBDateTimeValue.from(moonsetValue),
            moonPhase = moonPhase,
            temperature = DailyTemperatureModelData(
                morningTemperature = TemperatureForecastValue.from(temperatureMorningValue),
                dayTemperature = TemperatureForecastValue.from(temperatureDayValue),
                eveningTemperature = TemperatureForecastValue.from(temperatureEveningValue),
                nightTemperature = TemperatureForecastValue.from(temperatureNightValue),
                minDailyTemperature = TemperatureForecastValue.from(temperatureMinDailyValue),
                maxDailyTemperature = TemperatureForecastValue.from(temperatureMaxDailyValue)
            ),
            feelsLikeTemperature = DailyFeelsLikeTemperatureModelData(
                morningTemperature = FeelsLikeForecastValue.from(feelsLikeTemperatureMorningValue),
                dayTemperature = FeelsLikeForecastValue.from(feelsLikeTemperatureDayValue),
                eveningTemperature = FeelsLikeForecastValue.from(feelsLikeTemperatureEveningValue),
                nightTemperature = FeelsLikeForecastValue.from(feelsLikeTemperatureNightValue)
            ),
            pressure = PressureForecastValue.from(pressureValue),
            humidity = HumidityForecastValue.from(humidityValue),
            dewPointTemperature = DewPointForecastValue.from(dewPointTemperatureValue),
            windSpeed = WindSpeedForecastValue.from(windSpeedValue),
            windGust = WindGustForecastValue.from(windGustValue),
            windDegrees = WindDegreesForecastValue.from(windDegreesValue),
            cloudiness = CloudinessForecastValue.from(cloudinessValue),
            uvIndex = UVIndexForecastValue.from(uvIndexValue),
            probabilityOfPrecipitation = ProbabilityOfPrecipitationForecastValue.from(
                probabilityOfPrecipitationValue
            ),
            rainVolume = RainForecastValue.from(rainVolumeValue),
            snowVolume = SnowForecastValue.from(snowVolumeValue),
            weather = WeatherModelData(
                icon = weatherIcon,
                condition = weatherCondition
            )
        )
    }

    fun createTestDailyForecasts(
        size: Int
    ): List<DailyForecastModelData> {
        return List(size) { index ->
            DailyForecastModelData(
                forecastTime = NBDateTimeValue.from(testForecastTimeValue.addDays(index)),
                sunrise = NBDateTimeValue.from(testStartDateValue.addHours(index)),
                sunset = NBDateTimeValue.from(testEndDateValue.addHours(index)),
                moonrise = NBDateTimeValue.from(testStartDateValue.addHours(index + 1)),
                moonset = NBDateTimeValue.from(testEndDateValue.addHours(index + 1)),
                moonPhase = MoonPhaseType.values()[index],
                temperature = DailyTemperatureModelData(
                    morningTemperature = TemperatureForecastValue.from(index + 1.0),
                    dayTemperature = TemperatureForecastValue.from(index + 2.0),
                    eveningTemperature = TemperatureForecastValue.from(index + 3.0),
                    nightTemperature = TemperatureForecastValue.from(index + 4.0),
                    minDailyTemperature = TemperatureForecastValue.from(index - 10.0),
                    maxDailyTemperature = TemperatureForecastValue.from(index + 10.0)
                ),
                feelsLikeTemperature = DailyFeelsLikeTemperatureModelData(
                    morningTemperature = FeelsLikeForecastValue.from(index + 1.5),
                    dayTemperature = FeelsLikeForecastValue.from(index + 2.5),
                    eveningTemperature = FeelsLikeForecastValue.from(index + 3.5),
                    nightTemperature = FeelsLikeForecastValue.from(index + 4.5)
                ),
                pressure = PressureForecastValue.from(index + 1L),
                humidity = HumidityForecastValue.from(index + 2L),
                dewPointTemperature = DewPointForecastValue.from(index + 3.0),
                windSpeed = WindSpeedForecastValue.from(index + 4.0),
                windGust = WindGustForecastValue.from(index + 5.0),
                windDegrees = WindDegreesForecastValue.from(index + 6L),
                cloudiness = CloudinessForecastValue.from(index + 7L),
                uvIndex = UVIndexForecastValue.from(index + 8.0),
                probabilityOfPrecipitation = ProbabilityOfPrecipitationForecastValue.from(index + 9.0),
                rainVolume = RainForecastValue.from(index + 10.0),
                snowVolume = SnowForecastValue.from(index + 11.0),
                weather = WeatherModelData(
                    icon = WeatherIconType.values()[index],
                    condition = WeatherConditionType.values()[index]
                )
            )
        }
    }

    fun createTestNationalWeatherAlert(
        senderNameText: String? = "SenderName",
        eventNameText: String? = "EventName",
        startDateValue: Long? = testStartDateValue,
        endDateValue: Long? = testEndDateValue,
        descriptionText: String? = "Description",
        tagTexts: List<String>? = listOf("Tag 1", "Tag 2", "Tag 3")
    ): NationalWeatherAlertModelData {
        return NationalWeatherAlertModelData(
            senderName = createNBString(senderNameText),
            eventName = createNBString(eventNameText),
            startDate = NBDateTimeValue.from(startDateValue),
            endDate = NBDateTimeValue.from(endDateValue),
            description = createNBString(descriptionText),
            tags = tagTexts?.map { tagText -> createNBString(tagText)!! }
        )
    }

    fun createTestNationalWeatherAlerts(
        size: Int
    ): List<NationalWeatherAlertModelData> {
        return List(size) { index ->
            NationalWeatherAlertModelData(
                senderName = createNBString("SenderName $index"),
                eventName = createNBString("EventName $index"),
                startDate = NBDateTimeValue.from(testStartDateValue + index),
                endDate = NBDateTimeValue.from(testEndDateValue + index),
                description = createNBString("Description $index"),
                tags = List(2) { tagIndex ->
                    createNBString("Tag $tagIndex $index")!!
                }
            )
        }
    }

    fun createTestForecastValueUnits(value: Number): ForecastValue.Units =
        TemperatureForecastValue.from(value.toDouble())!!

}