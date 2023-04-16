package de.niklasbednarczyk.nbweather.data.onecall.repositories

import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbZip
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.*
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.common.WeatherModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.services.FakeOneCallService
import de.niklasbednarczyk.nbweather.data.onecall.remote.services.NBOneCallService
import de.niklasbednarczyk.nbweather.data.onecall.values.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.data.onecall.values.units.DistanceValue
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesValue
import de.niklasbednarczyk.nbweather.test.data.localremote.repositories.NBLocalRemoteRepositoryTest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class OneCallRepositoryTest : NBLocalRemoteRepositoryTest {

    companion object {
        private const val LATITUDE = 0.0
        private const val LONGITUDE = 0.0
        private val LANGUAGE = NBLanguageType.ENGLISH
        private val UNITS = NBUnitsType.METRIC
    }

    private lateinit var subject: OneCallRepository

    private lateinit var oneCallService: NBOneCallService

    private lateinit var currentWeatherDao: NBCurrentWeatherDao

    private lateinit var dailyForecastDao: NBDailyForecastDao

    private lateinit var hourlyForecastDao: NBHourlyForecastDao

    private lateinit var minutelyForecastDao: NBMinutelyForecastDao

    private lateinit var nationalWeatherAlertDao: NBNationalWeatherAlertDao

    private lateinit var oneCallDao: NBOneCallDao

    @Before
    override fun setUp() {
        currentWeatherDao = FakeCurrentWeatherDao()
        dailyForecastDao = FakeDailyForecastDao()
        hourlyForecastDao = FakeHourlyForecastDao()
        minutelyForecastDao = FakeMinutelyForecastDao()
        nationalWeatherAlertDao = FakeNationalWeatherAlertDao()
        oneCallDao = FakeOneCallDao(
            currentWeatherDao = currentWeatherDao,
            dailyForecastDao = dailyForecastDao,
            hourlyForecastDao = hourlyForecastDao,
            minutelyForecastDao = minutelyForecastDao,
            nationalWeatherAlertDao = nationalWeatherAlertDao,
        )
        oneCallService = FakeOneCallService(context)
        subject = OneCallRepository(
            oneCallService = oneCallService,
            currentWeatherDao = currentWeatherDao,
            dailyForecastDao = dailyForecastDao,
            hourlyForecastDao = hourlyForecastDao,
            minutelyForecastDao = minutelyForecastDao,
            nationalWeatherAlertDao = nationalWeatherAlertDao,
            oneCallDao = oneCallDao
        )
    }

    @Test
    fun getOneCall_shouldGetCorrectOneCall() = testScope.runTest {
        // Arrange
        val remote = oneCallService.getOneCall(
            latitude = LATITUDE,
            longitude = LONGITUDE,
            language = LANGUAGE.name,
            units = UNITS.name
        )

        // Act + Assert
        subject.getOneCall(
            latitude = LATITUDE,
            longitude = LONGITUDE,
            language = LANGUAGE,
            units = UNITS,
            forceUpdate = true
        ).assertResourceSuccess { data ->
            assertValue(remote.timezoneOffset, data.metadata.timezoneOffset?.value)

            assertValue(remote.current?.dt, data.currentWeather.currentTime?.value)
            assertValue(remote.current?.sunrise, data.currentWeather.sunrise?.value)
            assertValue(remote.current?.sunset, data.currentWeather.sunset?.value)
            assertValue(remote.current?.temp, data.currentWeather.currentTemperature?.value)
            assertValue(remote.current?.feelsLike, data.currentWeather.feelsLikeTemperature?.value)
            assertValue(remote.current?.pressure, data.currentWeather.pressure?.value)
            assertValue(remote.current?.humidity, data.currentWeather.humidity?.value)
            assertValue(remote.current?.dewPoint, data.currentWeather.dewPointTemperature?.value)
            assertValue(remote.current?.clouds, data.currentWeather.cloudiness?.value)
            assertValue(remote.current?.uvi, data.currentWeather.uvIndex?.value)
            assertFieldVisibility(remote.current?.visibility, data.currentWeather.visibility)
            assertValue(remote.current?.windSpeed, data.currentWeather.windSpeed?.value)
            assertValue(remote.current?.windGust, data.currentWeather.windGust?.value, true)
            assertFieldWindDegrees(remote.current?.windDeg, data.currentWeather.windDegrees)
            assertValue(remote.current?.rain?.oneH, data.currentWeather.rain1hVolume?.value, true)
            assertValue(remote.current?.snow?.oneH, data.currentWeather.snow1hVolume?.value, true)
            assertClassRemoteWeather(remote.current?.weather, data.currentWeather.weather)

            nbZip(remote.minutely, data.minutelyForecasts) { minutelyRemote, minutelyData ->
                assertValue(minutelyRemote.dt, minutelyData.forecastTime?.value)
                assertValue(minutelyRemote.precipitation, minutelyData.precipitation?.value)
            }

            nbZip(remote.hourly, data.hourlyForecasts) { hourlyRemote, hourlyData ->
                assertValue(hourlyRemote.dt, hourlyData.forecastTime?.value)
                assertValue(hourlyRemote.temp, hourlyData.temperature?.value)
                assertValue(hourlyRemote.feelsLike, hourlyData.feelsLikeTemperature?.value)
                assertValue(hourlyRemote.pressure, hourlyData.pressure?.value)
                assertValue(hourlyRemote.humidity, hourlyData.humidity?.value)
                assertValue(hourlyRemote.dewPoint, hourlyData.dewPointTemperature?.value)
                assertValue(hourlyRemote.uvi, hourlyData.uvIndex?.value)
                assertValue(hourlyRemote.clouds, hourlyData.cloudiness?.value)
                assertFieldVisibility(hourlyRemote.visibility, hourlyData.visibility)
                assertValue(hourlyRemote.windSpeed, hourlyData.windSpeed?.value)
                assertValue(hourlyRemote.windGust, hourlyData.windGust?.value, true)
                assertFieldWindDegrees(hourlyRemote.windDeg, hourlyData.windDegrees)
                assertValue(hourlyRemote.pop, hourlyData.probabilityOfPrecipitation?.value)
                assertValue(hourlyRemote.rain?.oneH, hourlyData.rain1hVolume?.value, true)
                assertValue(hourlyRemote.snow?.oneH, hourlyData.snow1hVolume?.value, true)
                assertClassRemoteWeather(hourlyRemote.weather, hourlyData.weather)
            }

            nbZip(remote.daily, data.dailyForecasts) { dailyRemote, dailyData ->
                assertValue(dailyRemote.dt, dailyData.forecastTime?.value)
                assertValue(dailyRemote.sunrise, dailyData.sunrise?.value)
                assertValue(dailyRemote.sunset, dailyData.sunset?.value)
                assertValue(dailyRemote.moonrise, dailyData.moonrise?.value)
                assertValue(dailyRemote.moonset, dailyData.moonset?.value)
                assertValue(MoonPhaseType.from(dailyRemote.moonPhase), dailyData.moonPhase?.type)
                assertValue(
                    dailyRemote.temp?.morn,
                    dailyData.temperature?.morningTemperature?.value
                )
                assertValue(dailyRemote.temp?.day, dailyData.temperature?.dayTemperature?.value)
                assertValue(dailyRemote.temp?.eve, dailyData.temperature?.eveningTemperature?.value)
                assertValue(dailyRemote.temp?.night, dailyData.temperature?.nightTemperature?.value)
                assertValue(
                    dailyRemote.temp?.min,
                    dailyData.temperature?.minDailyTemperature?.value
                )
                assertValue(
                    dailyRemote.temp?.max,
                    dailyData.temperature?.maxDailyTemperature?.value
                )
                assertValue(
                    dailyRemote.feelsLike?.morn,
                    dailyData.feelsLikeTemperature?.morningTemperature?.value
                )
                assertValue(
                    dailyRemote.feelsLike?.day,
                    dailyData.feelsLikeTemperature?.dayTemperature?.value
                )
                assertValue(
                    dailyRemote.feelsLike?.eve,
                    dailyData.feelsLikeTemperature?.eveningTemperature?.value
                )
                assertValue(
                    dailyRemote.feelsLike?.night,
                    dailyData.feelsLikeTemperature?.nightTemperature?.value
                )
                assertValue(dailyRemote.pressure, dailyData.pressure?.value)
                assertValue(dailyRemote.humidity, dailyData.humidity?.value)
                assertValue(dailyRemote.dewPoint, dailyData.dewPointTemperature?.value)
                assertValue(dailyRemote.windSpeed, dailyData.windSpeed?.value)
                assertValue(dailyRemote.windGust, dailyData.windGust?.value, true)
                assertFieldWindDegrees(dailyRemote.windDeg, dailyData.windDegrees)
                assertValue(dailyRemote.clouds, dailyData.cloudiness?.value)
                assertValue(dailyRemote.uvi, dailyData.uvIndex?.value)
                assertValue(dailyRemote.pop, dailyData.probabilityOfPrecipitation?.value)
                assertValue(dailyRemote.rain, dailyData.rainVolume?.value, true)
                assertValue(dailyRemote.snow, dailyData.snowVolume?.value, true)
                assertClassRemoteWeather(dailyRemote.weather, dailyData.weather)
            }

            nbZip(remote.alerts, data.nationalWeatherAlerts) { alertRemote, alertData ->
                assertValue(alertRemote.senderName, alertData.senderName?.asString(context))
                assertValue(alertRemote.event, alertData.eventName?.asString(context))
                assertValue(alertRemote.start, alertData.startDate?.value)
                assertValue(alertRemote.end, alertData.endDate?.value)
                assertValue(alertRemote.description, alertData.description?.asString(context))
                alertRemote.tags?.zip(alertData.tags ?: emptyList()) { tagRemote, tagData ->
                    assertValue(tagRemote, tagData.asString(context))
                }
            }
        }
    }

    @Test
    fun getOneCall_shouldRefreshOnDifferentLanguage() = testScope.runTest {
        // Arrange
        subject.getOneCall(
            latitude = LATITUDE,
            longitude = LONGITUDE,
            language = NBLanguageType.ENGLISH,
            units = UNITS,
            forceUpdate = false
        ).assertResourceSuccess {
            val timezoneOffsetLocal = modifyTimezoneOffset()

            // Act + Assert
            subject.getOneCall(
                latitude = LATITUDE,
                longitude = LONGITUDE,
                language = NBLanguageType.GERMAN,
                units = UNITS,
                forceUpdate = false
            ).assertResourceSuccess { data ->
                assertTimezoneOffset(timezoneOffsetLocal, data, false)
            }
        }
    }

    @Test
    fun getOneCall_shouldRefreshOnDifferentUnits() = testScope.runTest {
        // Arrange
        subject.getOneCall(
            latitude = LATITUDE,
            longitude = LONGITUDE,
            language = LANGUAGE,
            units = NBUnitsType.METRIC,
            forceUpdate = false
        ).assertResourceSuccess {
            val timezoneOffsetLocal = modifyTimezoneOffset()

            // Act + Assert
            subject.getOneCall(
                latitude = LATITUDE,
                longitude = LONGITUDE,
                language = LANGUAGE,
                units = NBUnitsType.IMPERIAL,
                forceUpdate = false
            ).assertResourceSuccess { data ->
                assertTimezoneOffset(timezoneOffsetLocal, data, false)
            }
        }
    }

    @Test
    fun getOneCall_shouldNotRefreshOnSameLanguageAndUnits() = testScope.runTest {
        // Arrange
        subject.getOneCall(
            latitude = LATITUDE,
            longitude = LONGITUDE,
            language = LANGUAGE,
            units = UNITS,
            forceUpdate = false
        ).assertResourceSuccess {
            val timezoneOffsetLocal = modifyTimezoneOffset()

            // Act + Assert
            subject.getOneCall(
                latitude = LATITUDE,
                longitude = LONGITUDE,
                language = LANGUAGE,
                units = UNITS,
                forceUpdate = false
            ).assertResourceSuccess { data ->
                assertTimezoneOffset(timezoneOffsetLocal, data, true)
            }
        }
    }

    @Test
    fun getOneCall_shouldRefreshOnForceUpdate() = testScope.runTest {
        // Arrange
        subject.getOneCall(
            latitude = LATITUDE,
            longitude = LONGITUDE,
            language = LANGUAGE,
            units = UNITS,
            forceUpdate = false
        ).assertResourceSuccess {
            val timezoneOffsetLocal = modifyTimezoneOffset()

            // Act + Assert
            subject.getOneCall(
                latitude = LATITUDE,
                longitude = LONGITUDE,
                language = LANGUAGE,
                units = UNITS,
                forceUpdate = true
            ).assertResourceSuccess { data ->
                assertTimezoneOffset(timezoneOffsetLocal, data, false)
            }
        }
    }

    @Test
    fun getOneCallLocal_shouldGetCorrectOneCall() = testScope.runTest {
        // Arrange
        subject.getOneCall(
            latitude = LATITUDE,
            longitude = LONGITUDE,
            language = LANGUAGE,
            units = UNITS,
            forceUpdate = false
        ).assertResourceSuccess { dataAssert ->
            // Act + Assert
            subject.getOneCallLocal(
                latitude = LATITUDE,
                longitude = LONGITUDE
            ).assertResourceSuccess { dataAct ->
                assertNotNull(dataAct)
                assertEquals(dataAssert, dataAct)
            }
            subject.getOneCallLocal(
                latitude = LATITUDE + 1,
                longitude = LONGITUDE + 1
            ).assertResourceSuccess { dataAct ->
                assertNull(dataAct)
                assertNotEquals(dataAssert, dataAct)
            }
        }
    }

    private suspend fun modifyTimezoneOffset(): Long? {
        val metadata = oneCallDao.getOneCall(latitude = LATITUDE, longitude = LONGITUDE)
            .firstOrNull()?.metadata?.copy(
                timezoneOffset = Long.MAX_VALUE
            )
        if (metadata != null) {
            oneCallDao.insertOneCall(metadata)
        }
        return metadata?.timezoneOffset
    }

    private fun assertTimezoneOffset(
        timezoneOffsetLocal: Long?,
        data: OneCallModelData,
        shouldBeEqual: Boolean
    ) {
        val timezoneOffsetData = data.metadata.timezoneOffset?.value
        assertNotNull(timezoneOffsetData)
        if (shouldBeEqual) {
            assertEquals(timezoneOffsetLocal, timezoneOffsetData)
        } else {
            assertNotEquals(timezoneOffsetLocal, timezoneOffsetData)
        }
    }

    private fun assertFieldVisibility(
        remoteOrLocal: Long?,
        data: DistanceValue?
    ) {
        assertValue(remoteOrLocal?.div(1000), data?.value)
    }

    private fun assertFieldWindDegrees(
        remoteOrLocal: Long?,
        data: WindDegreesValue?
    ) {
        assertValue(remoteOrLocal?.minus(180f), data?.rotationDegrees)
    }

    private fun assertClassRemoteWeather(
        remote: List<WeatherModelRemote>?,
        data: WeatherModelData?
    ) {
        val weatherRemote = remote?.firstOrNull()
        assertValue(
            weatherRemote?.description,
            data?.description?.value?.asString(context)?.lowercase()
        )
        assertValue(WeatherIconType.from(weatherRemote?.icon), data?.icon?.type)
    }

}