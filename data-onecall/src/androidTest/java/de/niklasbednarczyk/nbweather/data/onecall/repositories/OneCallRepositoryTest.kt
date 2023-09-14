package de.niklasbednarczyk.nbweather.data.onecall.repositories

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbZip
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.collectUntilResource
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.FakeCurrentWeatherDao
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.FakeDailyForecastDao
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.FakeHourlyForecastDao
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.FakeMinutelyForecastDao
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.FakeNationalWeatherAlertDao
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.FakeOneCallDao
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.NBCurrentWeatherDao
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.NBDailyForecastDao
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.NBHourlyForecastDao
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.NBMinutelyForecastDao
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.NBNationalWeatherAlertDao
import de.niklasbednarczyk.nbweather.data.onecall.local.daos.NBOneCallDao
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.common.WeatherModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.services.FakeOneCallService
import de.niklasbednarczyk.nbweather.data.onecall.remote.services.NBOneCallService
import de.niklasbednarczyk.nbweather.data.onecall.values.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesValue
import de.niklasbednarczyk.nbweather.test.data.localremote.repositories.NBLocalRemoteRepositoryTest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class OneCallRepositoryTest : NBLocalRemoteRepositoryTest {

    companion object {
        private const val LATITUDE = 0.0
        private const val LONGITUDE = 0.0

        private const val EXCLUDE = ""
        private const val LANGUAGE = "en"
        private const val UNITS = "metric"
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
            exclude = EXCLUDE,
            language = LANGUAGE,
            units = UNITS
        )

        // Act + Assert
        subject.getOneCall(
            latitude = LATITUDE,
            longitude = LONGITUDE,
            forceUpdate = true
        ).collectUntilResource { data ->
            assertValue(remote.current?.dt, data.currentWeather.currentTime?.dateTime)
            assertValue(remote.current?.sunrise, data.currentWeather.sunrise?.dateTime)
            assertValue(remote.current?.sunset, data.currentWeather.sunset?.dateTime)
            assertValue(remote.current?.temp, data.currentWeather.currentTemperature?.valueInternal)
            assertValue(
                remote.current?.feelsLike,
                data.currentWeather.feelsLikeTemperature?.valueInternal
            )
            assertValue(remote.current?.pressure, data.currentWeather.pressure?.value)
            assertValue(remote.current?.humidity, data.currentWeather.humidity?.value)
            assertValue(
                remote.current?.dewPoint,
                data.currentWeather.dewPointTemperature?.valueInternal
            )
            assertValue(remote.current?.clouds, data.currentWeather.cloudiness?.value)
            assertValue(remote.current?.uvi, data.currentWeather.uvIndex?.value)
            assertValue(remote.current?.visibility, data.currentWeather.visibility?.value)
            assertValue(remote.current?.windSpeed, data.currentWeather.windSpeed?.value)
            assertValue(remote.current?.windGust, data.currentWeather.windGust?.value, true)
            assertFieldWindDegrees(remote.current?.windDeg, data.currentWeather.windDegrees)
            assertValue(remote.current?.rain?.oneH, data.currentWeather.rain1hVolume?.value, true)
            assertValue(remote.current?.snow?.oneH, data.currentWeather.snow1hVolume?.value, true)
            assertClassRemoteWeather(remote.current?.weather, data.currentWeather.weather)

            nbZip(remote.minutely, data.minutelyForecasts) { minutelyRemote, minutelyData ->
                assertValue(minutelyRemote.dt, minutelyData.forecastTime?.dateTime)
                assertValue(minutelyRemote.precipitation, minutelyData.precipitation?.value)
            }

            nbZip(remote.hourly, data.hourlyForecasts) { hourlyRemote, hourlyData ->
                assertValue(hourlyRemote.dt, hourlyData.forecastTime?.dateTime)
                assertValue(hourlyRemote.temp, hourlyData.temperature?.valueInternal)
                assertValue(hourlyRemote.feelsLike, hourlyData.feelsLikeTemperature?.valueInternal)
                assertValue(hourlyRemote.pressure, hourlyData.pressure?.value)
                assertValue(hourlyRemote.humidity, hourlyData.humidity?.value)
                assertValue(hourlyRemote.dewPoint, hourlyData.dewPointTemperature?.valueInternal)
                assertValue(hourlyRemote.uvi, hourlyData.uvIndex?.value)
                assertValue(hourlyRemote.clouds, hourlyData.cloudiness?.value)
                assertValue(hourlyRemote.visibility, hourlyData.visibility?.value)
                assertValue(hourlyRemote.windSpeed, hourlyData.windSpeed?.value)
                assertValue(hourlyRemote.windGust, hourlyData.windGust?.value, true)
                assertFieldWindDegrees(hourlyRemote.windDeg, hourlyData.windDegrees)
                assertValue(hourlyRemote.pop, hourlyData.probabilityOfPrecipitation?.value)
                assertValue(hourlyRemote.rain?.oneH, hourlyData.rain1hVolume?.value, true)
                assertValue(hourlyRemote.snow?.oneH, hourlyData.snow1hVolume?.value, true)
                assertClassRemoteWeather(hourlyRemote.weather, hourlyData.weather)
            }

            nbZip(remote.daily, data.dailyForecasts) { dailyRemote, dailyData ->
                assertValue(dailyRemote.dt, dailyData.forecastTime?.dateTime)
                assertValue(dailyRemote.sunrise, dailyData.sunrise?.dateTime)
                assertValue(dailyRemote.sunset, dailyData.sunset?.dateTime)
                assertValue(dailyRemote.moonrise, dailyData.moonrise?.dateTime)
                assertValue(dailyRemote.moonset, dailyData.moonset?.dateTime)
                assertValue(MoonPhaseType.from(dailyRemote.moonPhase), dailyData.moonPhase?.type)
                assertValue(
                    dailyRemote.temp?.morn,
                    dailyData.temperature?.morningTemperature?.valueInternal
                )
                assertValue(
                    dailyRemote.temp?.day,
                    dailyData.temperature?.dayTemperature?.valueInternal
                )
                assertValue(
                    dailyRemote.temp?.eve,
                    dailyData.temperature?.eveningTemperature?.valueInternal
                )
                assertValue(
                    dailyRemote.temp?.night,
                    dailyData.temperature?.nightTemperature?.valueInternal
                )
                assertValue(
                    dailyRemote.temp?.min,
                    dailyData.temperature?.minDailyTemperature?.valueInternal
                )
                assertValue(
                    dailyRemote.temp?.max,
                    dailyData.temperature?.maxDailyTemperature?.valueInternal
                )
                assertValue(
                    dailyRemote.feelsLike?.morn,
                    dailyData.feelsLikeTemperature?.morningTemperature?.valueInternal
                )
                assertValue(
                    dailyRemote.feelsLike?.day,
                    dailyData.feelsLikeTemperature?.dayTemperature?.valueInternal
                )
                assertValue(
                    dailyRemote.feelsLike?.eve,
                    dailyData.feelsLikeTemperature?.eveningTemperature?.valueInternal
                )
                assertValue(
                    dailyRemote.feelsLike?.night,
                    dailyData.feelsLikeTemperature?.nightTemperature?.valueInternal
                )
                assertValue(dailyRemote.pressure, dailyData.pressure?.value)
                assertValue(dailyRemote.humidity, dailyData.humidity?.value)
                assertValue(dailyRemote.dewPoint, dailyData.dewPointTemperature?.valueInternal)
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
                assertValue(alertRemote.start, alertData.startDate?.dateTime)
                assertValue(alertRemote.end, alertData.endDate?.dateTime)
                assertValue(alertRemote.description, alertData.description?.asString(context))
                alertRemote.tags?.zip(alertData.tags ?: emptyList()) { tagRemote, tagData ->
                    assertValue(tagRemote, tagData.asString(context))
                }
            }
        }
    }

    @Test
    fun getOneCall_shouldRefreshOnForceUpdate() = testScope.runTest {
        // Arrange
        subject.getOneCall(
            latitude = LATITUDE,
            longitude = LONGITUDE,
            forceUpdate = false
        ).collectUntilResource {
            val currentWeatherDtLocal = modifyCurrentWeatherDt()

            // Act + Assert
            subject.getOneCall(
                latitude = LATITUDE,
                longitude = LONGITUDE,
                forceUpdate = true
            ).collectUntilResource { data ->
                assertCurrentWeatherDt(currentWeatherDtLocal, data)
            }
        }
    }

    private suspend fun modifyCurrentWeatherDt(): Long? {
        val currentWeather = oneCallDao.getOneCall(latitude = LATITUDE, longitude = LONGITUDE)
            .firstOrNull()?.currentWeather?.copy(
                dt = Long.MAX_VALUE
            )
        if (currentWeather != null) {
            currentWeatherDao.insertCurrentWeather(currentWeather)
        }
        return currentWeather?.dt
    }

    private fun assertCurrentWeatherDt(
        currentWeatherDtLocal: Long?,
        data: OneCallModelData,
    ) {
        val currentWeatherDtData = data.currentWeather.currentTime?.dateTime
        assertNotNull(currentWeatherDtData)
        assertNotEquals(currentWeatherDtLocal, currentWeatherDtData)
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
        assertValue(WeatherConditionType.from(weatherRemote?.id), data?.condition)
        assertValue(WeatherIconType.from(weatherRemote?.icon), data?.icon)
    }

}
