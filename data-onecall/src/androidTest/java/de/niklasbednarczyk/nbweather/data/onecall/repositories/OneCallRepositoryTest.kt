package de.niklasbednarczyk.nbweather.data.onecall.repositories

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbCollectUntilResource
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
import de.niklasbednarczyk.nbweather.data.onecall.remote.services.FakeOneCallService
import de.niklasbednarczyk.nbweather.data.onecall.remote.services.NBOneCallService
import de.niklasbednarczyk.nbweather.test.data.localremote.repositories.NBLocalRemoteRepositoryTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class OneCallRepositoryTest : NBLocalRemoteRepositoryTest {

    companion object {
        private const val LOCATION_1_LATITUDE = 40.7127281
        private const val LOCATION_1_LONGITUDE = -74.0060152

        private const val LOCATION_2_LATITUDE = 52.5170365
        private const val LOCATION_2_LONGITUDE = 13.3888599
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
        subject.getOneCall(
            latitude = LOCATION_1_LATITUDE,
            longitude = LOCATION_1_LONGITUDE
        ).nbCollectUntilResource { oneCall1 ->
            // Act
            subject.getOneCall(
                latitude = LOCATION_2_LATITUDE,
                longitude = LOCATION_2_LONGITUDE
            ).nbCollectUntilResource { oneCall2 ->
                assertNotEquals(oneCall1.timezoneOffset?.value, oneCall2.timezoneOffset?.value)
            }
        }
    }

    @Test
    fun refreshOneCall_shouldRefreshOneCall() = testScope.runTest {
        // Arrange
        val latitude = LOCATION_1_LATITUDE
        val longitude = LOCATION_1_LONGITUDE

        val currentTimeUpdate = Long.MAX_VALUE

        subject.getOneCall(
            latitude = latitude,
            longitude = longitude
        ).nbCollectUntilResource {
            val oneCallLocal = oneCallDao.getOneCall(
                latitude = latitude,
                longitude = longitude
            ).firstOrNull()
            val currentWeatherLocal = oneCallLocal!!.currentWeather!!.copy(
                dt = currentTimeUpdate
            )
            currentWeatherDao.insertCurrentWeather(currentWeatherLocal)

            val oneCallLocalBeforeRefresh = oneCallDao.getOneCall(
                latitude = latitude,
                longitude = longitude
            ).firstOrNull()

            delayForDifferentTimestamps()

            // Act
            val refreshResource = subject.refreshOneCall(
                latitude = latitude,
                longitude = longitude
            )

            val oneCallLocalAfterRefresh = oneCallDao.getOneCall(
                latitude = latitude,
                longitude = longitude
            ).firstOrNull()

            // Assert
            assertNotNull(oneCallLocalBeforeRefresh)
            assertNotNull(oneCallLocalAfterRefresh)

            assertNotEquals(
                oneCallLocalBeforeRefresh.metadata.id,
                oneCallLocalAfterRefresh.metadata.id
            )
            assertNotEquals(
                oneCallLocalBeforeRefresh.metadata.timestampEpochSeconds,
                oneCallLocalAfterRefresh.metadata.timestampEpochSeconds
            )

            assertEquals(currentTimeUpdate, oneCallLocalBeforeRefresh.currentWeather?.dt)

            assertNotEquals(currentTimeUpdate, oneCallLocalAfterRefresh.currentWeather?.dt)

            assertIsClass(refreshResource, NBResource.Success::class.java)
        }
    }

}
