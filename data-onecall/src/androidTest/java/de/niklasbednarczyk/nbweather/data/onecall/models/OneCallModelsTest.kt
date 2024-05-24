package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbMap
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.data.onecall.local.models.CurrentWeatherEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.DailyForecastEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.HourlyForecastEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.MinutelyForecastEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.NationalWeatherAlertEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallMetadataEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallMetadataEntityLocal.Companion.coordinates
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.common.WeatherModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.daily.DailyFeelsLikeTemperatureModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.daily.DailyTemperatureModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.CurrentWeatherModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.DailyForecastModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.HourlyForecastModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.MinutelyForecastModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.NationalWeatherAlertModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.OneCallModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.common.PrecipitationModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.common.WeatherModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.daily.DailyFeelsLikeTemperatureModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.daily.DailyTemperatureModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test
import kotlin.test.assertNotNull

class OneCallModelsTest : NBTest {

    companion object {

        private const val CONDITION_THUNDERSTORM_WITH_LIGHT_RAIN = 200L

        private const val ICON_D_CLEAR_SKY = "01d"

        private const val MOON_PHASE_NEW_MOON = 0.0

    }

    @Test
    fun localToData_shouldConvertCorrectly() {
        // Arrange
        val oneCallLocal = createTestOneCallLocal()
        val currentWeatherLocal = oneCallLocal.currentWeather
        val minutelyForecastsLocal = oneCallLocal.minutelyForecasts
        val hourlyForecastsLocal = oneCallLocal.hourlyForecasts
        val dailyForecastsLocal = oneCallLocal.dailyForecasts
        val nationalWeatherAlertsLocal = oneCallLocal.nationalWeatherAlerts

        // Act
        val oneCallData = OneCallModelData.localToData(oneCallLocal)
        val currentWeatherData = oneCallData.currentWeather
        val minutelyForecastsData = oneCallData.minutelyForecasts
        val hourlyForecastsData = oneCallData.hourlyForecasts
        val dailyForecastsData = oneCallData.dailyForecasts
        val nationalWeatherAlertsData = oneCallData.nationalWeatherAlerts

        // Assert
        fun assertWeather(
            weatherLocal: WeatherModelLocal?,
            weatherData: WeatherModelData?
        ) {
            assertNotNull(weatherLocal)
            assertNotNull(weatherData)

            assertValue(WeatherIconType.D_CLEAR_SKY, weatherData.icon)
            assertValue(WeatherConditionType.THUNDERSTORM_WITH_LIGHT_RAIN, weatherData.condition)
        }

        assertValue(oneCallLocal.metadata.coordinates, oneCallData.coordinates)
        assertValue(oneCallLocal.metadata.timestampEpochSeconds, oneCallData.timestamp.value)
        assertValue(oneCallLocal.metadata.timezoneOffset, oneCallData.timezoneOffset?.value)

        assertNotNull(currentWeatherLocal)
        assertValue(currentWeatherLocal.dt, currentWeatherData.currentTime?.value)
        assertValue(currentWeatherLocal.sunrise, currentWeatherData.sunrise?.value)
        assertValue(currentWeatherLocal.sunset, currentWeatherData.sunset?.value)
        assertValue(
            currentWeatherLocal.temp,
            currentWeatherData.currentTemperature?.unitsValue?.value
        )
        assertValue(
            currentWeatherLocal.feelsLike,
            currentWeatherData.feelsLikeTemperature?.unitsValue?.value
        )
        assertValue(currentWeatherLocal.pressure, currentWeatherData.pressure?.unitsValue?.value)
        assertValue(currentWeatherLocal.humidity, currentWeatherData.humidity?.unitsValue?.value)
        assertValue(
            currentWeatherLocal.dewPoint,
            currentWeatherData.dewPointTemperature?.unitsValue?.value
        )
        assertValue(currentWeatherLocal.clouds, currentWeatherData.cloudiness?.unitsValue?.value)
        assertValue(currentWeatherLocal.uvi, currentWeatherData.uvIndex?.unitsValue?.value)
        assertValue(
            currentWeatherLocal.visibility,
            currentWeatherData.visibility?.unitsValue?.value
        )
        assertValue(currentWeatherLocal.windSpeed, currentWeatherData.windSpeed?.unitsValue?.value)
        assertValue(currentWeatherLocal.windGust, currentWeatherData.windGust?.unitsValue?.value)
        assertValue(currentWeatherLocal.windDeg, currentWeatherData.windDegrees?.value)
        assertValue(currentWeatherLocal.rain1h, currentWeatherData.rain1hVolume?.unitsValue?.value)
        assertValue(currentWeatherLocal.snow1h, currentWeatherData.snow1hVolume?.unitsValue?.value)
        assertWeather(currentWeatherLocal.weather, currentWeatherData.weather)

        assertListValues(
            minutelyForecastsLocal,
            minutelyForecastsData
        ) { minutelyLocal, minutelyData ->
            assertValue(minutelyLocal.dt, minutelyData.forecastTime?.value)
            assertValue(minutelyLocal.precipitation, minutelyData.precipitation?.value)
        }

        assertListValues(
            hourlyForecastsLocal,
            hourlyForecastsData
        ) { hourlyLocal, hourlyData ->
            assertValue(hourlyLocal.dt, hourlyData.forecastTime?.value)
            assertValue(hourlyLocal.temp, hourlyData.temperature?.unitsValue?.value)
            assertValue(hourlyLocal.feelsLike, hourlyData.feelsLikeTemperature?.unitsValue?.value)
            assertValue(hourlyLocal.pressure, hourlyData.pressure?.unitsValue?.value)
            assertValue(hourlyLocal.humidity, hourlyData.humidity?.unitsValue?.value)
            assertValue(hourlyLocal.dewPoint, hourlyData.dewPointTemperature?.unitsValue?.value)
            assertValue(hourlyLocal.uvi, hourlyData.uvIndex?.unitsValue?.value)
            assertValue(hourlyLocal.clouds, hourlyData.cloudiness?.unitsValue?.value)
            assertValue(hourlyLocal.visibility, hourlyData.visibility?.unitsValue?.value)
            assertValue(hourlyLocal.windSpeed, hourlyData.windSpeed?.unitsValue?.value)
            assertValue(hourlyLocal.windGust, hourlyData.windGust?.unitsValue?.value)
            assertValue(hourlyLocal.windDeg, hourlyData.windDegrees?.value)
            assertValue(hourlyLocal.pop, hourlyData.probabilityOfPrecipitation?.unitsValue?.value)
            assertValue(hourlyLocal.rain1h, hourlyData.rain1hVolume?.unitsValue?.value)
            assertValue(hourlyLocal.snow1h, hourlyData.snow1hVolume?.unitsValue?.value)
            assertWeather(hourlyLocal.weather, hourlyData.weather)
        }

        assertListValues(
            dailyForecastsLocal,
            dailyForecastsData
        ) { dailyLocal, dailyData ->
            assertValue(dailyLocal.dt, dailyData.forecastTime?.value)
            assertValue(dailyLocal.sunrise, dailyData.sunrise?.value)
            assertValue(dailyLocal.sunset, dailyData.sunset?.value)
            assertValue(dailyLocal.moonrise, dailyData.moonrise?.value)
            assertValue(dailyLocal.moonset, dailyData.moonset?.value)
            assertValue(MoonPhaseType.NEW_MOON, dailyData.moonPhase)
            assertValue(dailyLocal.summary, dailyData.summary.asString(context))
            assertValue(
                dailyLocal.temp?.morn,
                dailyData.temperature?.morningTemperature?.unitsValue?.value
            )
            assertValue(
                dailyLocal.temp?.day,
                dailyData.temperature?.dayTemperature?.unitsValue?.value
            )
            assertValue(
                dailyLocal.temp?.eve,
                dailyData.temperature?.eveningTemperature?.unitsValue?.value
            )
            assertValue(
                dailyLocal.temp?.night,
                dailyData.temperature?.nightTemperature?.unitsValue?.value
            )
            assertValue(
                dailyLocal.temp?.min,
                dailyData.temperature?.minDailyTemperature?.unitsValue?.value
            )
            assertValue(
                dailyLocal.temp?.max,
                dailyData.temperature?.maxDailyTemperature?.unitsValue?.value
            )
            assertValue(
                dailyLocal.feelsLike?.morn,
                dailyData.feelsLikeTemperature?.morningTemperature?.unitsValue?.value
            )
            assertValue(
                dailyLocal.feelsLike?.day,
                dailyData.feelsLikeTemperature?.dayTemperature?.unitsValue?.value
            )
            assertValue(
                dailyLocal.feelsLike?.eve,
                dailyData.feelsLikeTemperature?.eveningTemperature?.unitsValue?.value
            )
            assertValue(
                dailyLocal.feelsLike?.night,
                dailyData.feelsLikeTemperature?.nightTemperature?.unitsValue?.value
            )
            assertValue(dailyLocal.pressure, dailyData.pressure?.unitsValue?.value)
            assertValue(dailyLocal.humidity, dailyData.humidity?.unitsValue?.value)
            assertValue(dailyLocal.dewPoint, dailyData.dewPointTemperature?.unitsValue?.value)
            assertValue(dailyLocal.windSpeed, dailyData.windSpeed?.unitsValue?.value)
            assertValue(dailyLocal.windGust, dailyData.windGust?.unitsValue?.value)
            assertValue(dailyLocal.windDeg, dailyData.windDegrees?.value)
            assertValue(dailyLocal.clouds, dailyData.cloudiness?.unitsValue?.value)
            assertValue(dailyLocal.uvi, dailyData.uvIndex?.unitsValue?.value)
            assertValue(dailyLocal.pop, dailyData.probabilityOfPrecipitation?.unitsValue?.value)
            assertValue(dailyLocal.rain, dailyData.rainVolume?.unitsValue?.value)
            assertValue(dailyLocal.snow, dailyData.snowVolume?.unitsValue?.value)
            assertWeather(dailyLocal.weather, dailyData.weather)
        }

        assertListValues(
            nationalWeatherAlertsLocal,
            nationalWeatherAlertsData
        ) { alertLocal, alertData ->
            val alertDataTagsString = alertData.tags.nbMap { tag -> tag.asString(context) }

            assertValue(alertLocal.senderName, alertData.senderName.asString(context))
            assertValue(alertLocal.event, alertData.eventName.asString(context))
            assertValue(alertLocal.start, alertData.startDate?.value)
            assertValue(alertLocal.end, alertData.endDate?.value)
            assertValue(alertLocal.description, alertData.description.asString(context))
            assertListsContainSameItems(alertLocal.tags, alertDataTagsString)
        }
    }

    @Test
    fun remoteToLocal_shouldConvertCorrectly() {
        // Arrange
        val oneCallRemote = createTestOneCallRemote()
        val currentWeatherRemote = oneCallRemote.current
        val minutelyForecastsRemote = oneCallRemote.minutely
        val hourlyForecastsRemote = oneCallRemote.hourly
        val dailyForecastsRemote = oneCallRemote.daily
        val nationalWeatherAlertsRemote = oneCallRemote.alerts

        val metadataId = 1L
        val latitude = -1.0
        val longitude = -2.0

        // Act
        val metadataLocal = OneCallModelData.remoteToLocal(
            remote = oneCallRemote,
            latitude = latitude,
            longitude = longitude
        )
        val currentWeatherLocal = CurrentWeatherModelData.remoteToLocal(
            remote = currentWeatherRemote,
            metadataId = metadataId
        )
        val minutelyForecastsLocal = MinutelyForecastModelData.remoteToLocal(
            remote = minutelyForecastsRemote,
            metadataId = metadataId
        )
        val hourlyForecastsLocal = HourlyForecastModelData.remoteToLocal(
            remote = hourlyForecastsRemote,
            metadataId = metadataId
        )
        val dailyForecastsLocal = DailyForecastModelData.remoteToLocal(
            remote = dailyForecastsRemote,
            metadataId = metadataId
        )
        val nationalWeatherAlertsLocal = NationalWeatherAlertModelData.remoteToLocal(
            remote = nationalWeatherAlertsRemote,
            metadataId = metadataId
        )

        // Assert
        fun assertWeather(
            weatherRemote: List<WeatherModelRemote>?,
            weatherLocal: WeatherModelLocal?
        ) {
            val weatherR = weatherRemote?.firstOrNull()
            assertNotNull(weatherR)
            assertNotNull(weatherLocal)

            assertValue(weatherR.id, weatherLocal.id)
            assertValue(weatherR.main, weatherLocal.main)
            assertValue(weatherR.description, weatherLocal.description)
            assertValue(weatherR.icon, weatherLocal.icon)
        }

        assertValue(latitude, metadataLocal.latitude)
        assertValue(longitude, metadataLocal.longitude)
        assertValue(oneCallRemote.timezone, metadataLocal.timezone)
        assertValue(oneCallRemote.timezoneOffset, metadataLocal.timezoneOffset)

        assertNotNull(currentWeatherRemote)
        assertValue(metadataId, currentWeatherLocal.metadataId)
        assertValue(currentWeatherRemote.dt, currentWeatherLocal.dt)
        assertValue(currentWeatherRemote.sunrise, currentWeatherLocal.sunrise)
        assertValue(currentWeatherRemote.sunset, currentWeatherLocal.sunset)
        assertValue(currentWeatherRemote.temp, currentWeatherLocal.temp)
        assertValue(currentWeatherRemote.feelsLike, currentWeatherLocal.feelsLike)
        assertValue(currentWeatherRemote.pressure, currentWeatherLocal.pressure)
        assertValue(currentWeatherRemote.humidity, currentWeatherLocal.humidity)
        assertValue(currentWeatherRemote.dewPoint, currentWeatherLocal.dewPoint)
        assertValue(currentWeatherRemote.clouds, currentWeatherLocal.clouds)
        assertValue(currentWeatherRemote.uvi, currentWeatherLocal.uvi)
        assertValue(currentWeatherRemote.visibility, currentWeatherLocal.visibility)
        assertValue(currentWeatherRemote.windSpeed, currentWeatherLocal.windSpeed)
        assertValue(currentWeatherRemote.windGust, currentWeatherLocal.windGust)
        assertValue(currentWeatherRemote.windDeg, currentWeatherLocal.windDeg)
        assertValue(currentWeatherRemote.rain?.oneH, currentWeatherLocal.rain1h)
        assertValue(currentWeatherRemote.snow?.oneH, currentWeatherLocal.snow1h)
        assertWeather(currentWeatherRemote.weather, currentWeatherLocal.weather)

        assertListValues(
            minutelyForecastsRemote,
            minutelyForecastsLocal
        ) { minutelyRemote, minutelyLocal ->
            assertValue(metadataId, minutelyLocal.metadataId)
            assertValue(minutelyRemote.dt, minutelyLocal.dt)
            assertValue(minutelyRemote.precipitation, minutelyLocal.precipitation)
        }

        assertListValues(
            hourlyForecastsRemote,
            hourlyForecastsLocal
        ) { hourlyRemote, hourlyLocal ->
            assertValue(metadataId, hourlyLocal.metadataId)
            assertValue(hourlyRemote.dt, hourlyLocal.dt)
            assertValue(hourlyRemote.temp, hourlyLocal.temp)
            assertValue(hourlyRemote.feelsLike, hourlyLocal.feelsLike)
            assertValue(hourlyRemote.pressure, hourlyLocal.pressure)
            assertValue(hourlyRemote.humidity, hourlyLocal.humidity)
            assertValue(hourlyRemote.dewPoint, hourlyLocal.dewPoint)
            assertValue(hourlyRemote.uvi, hourlyLocal.uvi)
            assertValue(hourlyRemote.clouds, hourlyLocal.clouds)
            assertValue(hourlyRemote.visibility, hourlyLocal.visibility)
            assertValue(hourlyRemote.windSpeed, hourlyLocal.windSpeed)
            assertValue(hourlyRemote.windGust, hourlyLocal.windGust)
            assertValue(hourlyRemote.windDeg, hourlyLocal.windDeg)
            assertValue(hourlyRemote.pop, hourlyLocal.pop)
            assertValue(hourlyRemote.rain?.oneH, hourlyLocal.rain1h)
            assertValue(hourlyRemote.snow?.oneH, hourlyLocal.snow1h)
            assertWeather(hourlyRemote.weather, hourlyLocal.weather)
        }

        assertListValues(
            dailyForecastsRemote,
            dailyForecastsLocal
        ) { dailyRemote, dailyLocal ->
            assertValue(metadataId, dailyLocal.metadataId)
            assertValue(dailyRemote.dt, dailyLocal.dt)
            assertValue(dailyRemote.sunrise, dailyLocal.sunrise)
            assertValue(dailyRemote.sunset, dailyLocal.sunset)
            assertValue(dailyRemote.moonrise, dailyLocal.moonrise)
            assertValue(dailyRemote.moonset, dailyLocal.moonset)
            assertValue(dailyRemote.moonPhase, dailyLocal.moonPhase)
            assertValue(dailyRemote.summary, dailyLocal.summary)
            assertValue(dailyRemote.temp?.morn, dailyLocal.temp?.morn)
            assertValue(dailyRemote.temp?.day, dailyLocal.temp?.day)
            assertValue(dailyRemote.temp?.eve, dailyLocal.temp?.eve)
            assertValue(dailyRemote.temp?.night, dailyLocal.temp?.night)
            assertValue(dailyRemote.temp?.min, dailyLocal.temp?.min)
            assertValue(dailyRemote.temp?.max, dailyLocal.temp?.max)
            assertValue(dailyRemote.feelsLike?.morn, dailyLocal.feelsLike?.morn)
            assertValue(dailyRemote.feelsLike?.day, dailyLocal.feelsLike?.day)
            assertValue(dailyRemote.feelsLike?.eve, dailyLocal.feelsLike?.eve)
            assertValue(dailyRemote.feelsLike?.night, dailyLocal.feelsLike?.night)
            assertValue(dailyRemote.pressure, dailyLocal.pressure)
            assertValue(dailyRemote.humidity, dailyLocal.humidity)
            assertValue(dailyRemote.dewPoint, dailyLocal.dewPoint)
            assertValue(dailyRemote.windSpeed, dailyLocal.windSpeed)
            assertValue(dailyRemote.windGust, dailyLocal.windGust)
            assertValue(dailyRemote.windDeg, dailyLocal.windDeg)
            assertValue(dailyRemote.clouds, dailyLocal.clouds)
            assertValue(dailyRemote.uvi, dailyLocal.uvi)
            assertValue(dailyRemote.pop, dailyLocal.pop)
            assertValue(dailyRemote.rain, dailyLocal.rain)
            assertValue(dailyRemote.snow, dailyLocal.snow)
            assertWeather(dailyRemote.weather, dailyLocal.weather)
        }

        assertListValues(
            nationalWeatherAlertsRemote,
            nationalWeatherAlertsLocal
        ) { alertRemote, alertLocal ->
            assertValue(metadataId, alertLocal.metadataId)
            assertValue(alertRemote.senderName, alertLocal.senderName)
            assertValue(alertRemote.event, alertLocal.event)
            assertValue(alertRemote.start, alertLocal.start)
            assertValue(alertRemote.end, alertLocal.end)
            assertValue(alertRemote.description, alertLocal.description)
            assertListsContainSameItems(alertRemote.tags, alertLocal.tags)
        }
    }

    private fun createTestOneCallLocal(): OneCallModelLocal {

        fun createWeather(
            id: Long
        ): WeatherModelLocal {
            return WeatherModelLocal(
                id = CONDITION_THUNDERSTORM_WITH_LIGHT_RAIN,
                main = "$id main",
                description = "$id description",
                icon = ICON_D_CLEAR_SKY
            )
        }

        fun createMetadata(
            id: Long
        ): OneCallMetadataEntityLocal {
            return OneCallMetadataEntityLocal(
                id = id,
                latitude = id + 1.0,
                longitude = id + 2.0,
                timezone = "$id timezone",
                timezoneOffset = id + 3
            )
        }

        fun createCurrent(
            metadataId: Long,
            id: Long
        ): CurrentWeatherEntityLocal {
            return CurrentWeatherEntityLocal(
                metadataId = metadataId,
                dt = id,
                sunrise = id + 1,
                sunset = id + 2,
                temp = id + 3.0,
                feelsLike = id + 4.0,
                pressure = id + 5,
                humidity = id + 6,
                dewPoint = id + 7.0,
                clouds = id + 8,
                uvi = id + 9.0,
                visibility = id + 10,
                windSpeed = id + 11.0,
                windGust = id + 12.0,
                windDeg = id + 13,
                rain1h = id + 14.0,
                snow1h = id + 15.0,
                weather = createWeather(id + 16)
            )
        }

        fun createMinutely(
            metadataId: Long,
            id: Long
        ): MinutelyForecastEntityLocal {
            return MinutelyForecastEntityLocal(
                metadataId = metadataId,
                dt = id,
                precipitation = id + 1.0
            )
        }

        fun createHourly(
            metadataId: Long,
            id: Long
        ): HourlyForecastEntityLocal {
            return HourlyForecastEntityLocal(
                metadataId = metadataId,
                dt = id,
                temp = id + 1.0,
                feelsLike = id + 2.0,
                pressure = id + 3,
                humidity = id + 4,
                dewPoint = id + 5.0,
                uvi = id + 6.0,
                clouds = id + 7,
                visibility = id + 8,
                windSpeed = id + 10.0,
                windGust = id + 11.0,
                windDeg = id + 12,
                pop = id + 13.0,
                rain1h = id + 14.0,
                snow1h = id + 15.0,
                weather = createWeather(id + 16)
            )
        }

        fun createDaily(
            metadataId: Long,
            id: Long
        ): DailyForecastEntityLocal {
            return DailyForecastEntityLocal(
                metadataId = metadataId,
                dt = id,
                sunrise = id + 1,
                sunset = id + 2,
                moonrise = id + 3,
                moonset = id + 4,
                moonPhase = MOON_PHASE_NEW_MOON,
                summary = "$id summary",
                temp = DailyTemperatureModelLocal(
                    morn = id + 5.0,
                    day = id + 6.0,
                    eve = id + 7.0,
                    night = id + 8.0,
                    min = id + 9.0,
                    max = id + 10.0
                ),
                feelsLike = DailyFeelsLikeTemperatureModelLocal(
                    morn = id + 11.0,
                    day = id + 12.0,
                    eve = id + 13.0,
                    night = id + 14.0,
                ),
                pressure = id + 15,
                humidity = id + 16,
                dewPoint = id + 17.0,
                windSpeed = id + 18.0,
                windGust = id + 19.0,
                windDeg = id + 20,
                clouds = id + 21,
                uvi = id + 22.0,
                pop = id + 23.0,
                rain = id + 24.0,
                snow = id + 25.0,
                weather = createWeather(id + 26)
            )
        }

        fun createAlert(
            metadataId: Long,
            id: Long
        ): NationalWeatherAlertEntityLocal {
            return NationalWeatherAlertEntityLocal(
                metadataId = metadataId,
                senderName = "$id senderName",
                event = "$id event",
                start = id,
                end = id + 1,
                description = "$id description",
                tags = listOf(
                    "$id tag 1",
                    "$id tag 2"
                )
            )
        }

        val metadataId = 1L

        return OneCallModelLocal(
            metadata = createMetadata(metadataId),
            currentWeather = createCurrent(metadataId, 2),
            minutelyForecasts = listOf(
                createMinutely(metadataId, 3),
                createMinutely(metadataId, 4)
            ),
            hourlyForecasts = listOf(
                createHourly(metadataId, 5),
                createHourly(metadataId, 6)
            ),
            dailyForecasts = listOf(
                createDaily(metadataId, 7),
                createDaily(metadataId, 8)
            ),
            nationalWeatherAlerts = listOf(
                createAlert(metadataId, 9),
                createAlert(metadataId, 10)
            )
        )
    }

    private fun createTestOneCallRemote(): OneCallModelRemote {

        fun createWeather(
            prefix: String,
            id: Long,
        ): WeatherModelRemote {
            return WeatherModelRemote(
                id = id,
                main = "$prefix $id main",
                description = "$prefix $id description",
                icon = "$prefix $id icon"
            )
        }

        fun createCurrent(
            id: Long
        ): CurrentWeatherModelRemote {
            return CurrentWeatherModelRemote(
                dt = id,
                sunrise = id + 1,
                sunset = id + 2,
                temp = id + 3.0,
                feelsLike = id + 4.0,
                pressure = id + 5,
                humidity = id + 6,
                dewPoint = id + 7.0,
                clouds = id + 8,
                uvi = id + 9.0,
                visibility = id + 10,
                windSpeed = id + 11.0,
                windGust = id + 12.0,
                windDeg = id + 13,
                rain = PrecipitationModelRemote(id + 14.0),
                snow = PrecipitationModelRemote(id + 15.0),
                weather = listOf(
                    createWeather("current", id + 16),
                    createWeather("current", id + 17)
                )
            )
        }

        fun createMinutely(
            id: Long
        ): MinutelyForecastModelRemote {
            return MinutelyForecastModelRemote(
                dt = id,
                precipitation = id + 1.0
            )
        }

        fun createHourly(
            id: Long
        ): HourlyForecastModelRemote {
            return HourlyForecastModelRemote(
                dt = id,
                temp = id + 1.0,
                feelsLike = id + 2.0,
                pressure = id + 3,
                humidity = id + 4,
                dewPoint = id + 5.0,
                uvi = id + 6.0,
                clouds = id + 7,
                visibility = id + 8,
                windSpeed = id + 10.0,
                windGust = id + 11.0,
                windDeg = id + 12,
                pop = id + 13.0,
                rain = PrecipitationModelRemote(id + 14.0),
                snow = PrecipitationModelRemote(id + 15.0),
                weather = listOf(
                    createWeather("hourly", id + 16),
                    createWeather("hourly", id + 17)
                )
            )
        }

        fun createDaily(
            id: Long
        ): DailyForecastModelRemote {
            return DailyForecastModelRemote(
                dt = id,
                sunrise = id + 1,
                sunset = id + 2,
                moonrise = id + 3,
                moonset = id + 4,
                moonPhase = id + 5.0,
                summary = "$id summary",
                temp = DailyTemperatureModelRemote(
                    morn = id + 6.0,
                    day = id + 7.0,
                    eve = id + 8.0,
                    night = id + 9.0,
                    min = id + 10.0,
                    max = id + 11.0
                ),
                feelsLike = DailyFeelsLikeTemperatureModelRemote(
                    morn = id + 12.0,
                    day = id + 13.0,
                    eve = id + 14.0,
                    night = id + 15.0,
                ),
                pressure = id + 16,
                humidity = id + 17,
                dewPoint = id + 18.0,
                windSpeed = id + 19.0,
                windGust = id + 20.0,
                windDeg = id + 21,
                clouds = id + 22,
                uvi = id + 23.0,
                pop = id + 24.0,
                rain = id + 25.0,
                snow = id + 26.0,
                weather = listOf(
                    createWeather("daily", id + 27),
                    createWeather("daily", id + 28)
                )
            )
        }

        fun createAlert(
            id: Long
        ): NationalWeatherAlertModelRemote {
            return NationalWeatherAlertModelRemote(
                senderName = "$id senderName",
                event = "$id event",
                start = id,
                end = id + 1,
                description = "$id description",
                tags = listOf(
                    "$id tag 1",
                    "$id tag 2"
                )
            )
        }

        return OneCallModelRemote(
            lat = 1.0,
            lon = 2.0,
            timezone = "timezone",
            timezoneOffset = 3,
            current = createCurrent(4),
            minutely = listOf(
                createMinutely(5),
                createMinutely(6)
            ),
            hourly = listOf(
                createHourly(7),
                createHourly(8)
            ),
            daily = listOf(
                createDaily(9),
                createDaily(10)
            ),
            alerts = listOf(
                createAlert(11),
                createAlert(12)
            )
        )
    }

}