package de.niklasbednarczyk.nbweather.data.onecall.repositories

import android.content.Context
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.LocalRemoteOfflineGetMediator
import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.LocalRemoteOfflineRefreshMediator
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.constants.ConstantsCoreRemote
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
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.models.CurrentWeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.HourlyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.MinutelyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.NationalWeatherAlertModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.OneCallModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.services.FakeOneCallService
import de.niklasbednarczyk.nbweather.data.onecall.remote.services.NBOneCallService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OneCallRepository @Inject constructor(
    private val oneCallService: NBOneCallService,
    private val currentWeatherDao: NBCurrentWeatherDao,
    private val dailyForecastDao: NBDailyForecastDao,
    private val hourlyForecastDao: NBHourlyForecastDao,
    private val minutelyForecastDao: NBMinutelyForecastDao,
    private val nationalWeatherAlertDao: NBNationalWeatherAlertDao,
    private val oneCallDao: NBOneCallDao
) {

    companion object {

        fun createFake(
            context: Context
        ): OneCallRepository {
            val currentWeatherDao = FakeCurrentWeatherDao()
            val dailyForecastDao = FakeDailyForecastDao()
            val hourlyForecastDao = FakeHourlyForecastDao()
            val minutelyForecastDao = FakeMinutelyForecastDao()
            val nationalWeatherAlertDao = FakeNationalWeatherAlertDao()

            return OneCallRepository(
                oneCallService = FakeOneCallService(context),
                currentWeatherDao = currentWeatherDao,
                dailyForecastDao = dailyForecastDao,
                hourlyForecastDao = hourlyForecastDao,
                minutelyForecastDao = minutelyForecastDao,
                nationalWeatherAlertDao = nationalWeatherAlertDao,
                oneCallDao = FakeOneCallDao(
                    currentWeatherDao = currentWeatherDao,
                    dailyForecastDao = dailyForecastDao,
                    hourlyForecastDao = hourlyForecastDao,
                    minutelyForecastDao = minutelyForecastDao,
                    nationalWeatherAlertDao = nationalWeatherAlertDao
                )
            )
        }
    }

    suspend fun getOneCall(
        coordinates: NBCoordinatesModel?
    ): Flow<NBResource<OneCallModelData>> {
        if (coordinates == null) return flowOf(NBResource.Error())

        return object :
            LocalRemoteOfflineGetMediator<OneCallModelData, OneCallModelLocal, OneCallModelRemote>() {
            override fun getLocal(): Flow<OneCallModelLocal?> {
                return getLocal(
                    coordinates = coordinates
                )
            }

            override suspend fun getRemote(): OneCallModelRemote {
                return getRemote(
                    coordinates = coordinates
                )
            }

            override fun localToData(local: OneCallModelLocal): OneCallModelData {
                return OneCallModelData.localToData(local)
            }

            override fun shouldGetRemote(local: OneCallModelLocal): Boolean {
                return local.metadata.isExpired
            }

            override fun clearLocal(local: OneCallModelLocal) {
                this@OneCallRepository.clearLocal(local)
            }

            override fun insertLocal(remote: OneCallModelRemote) {
                insertLocal(
                    remote = remote,
                    coordinates = coordinates
                )
            }
        }()
    }

    suspend fun refreshOneCall(
        coordinates: NBCoordinatesModel
    ): NBResource<Unit> {
        return object : LocalRemoteOfflineRefreshMediator<OneCallModelLocal, OneCallModelRemote>() {

            override fun clearLocal(local: OneCallModelLocal) {
                this@OneCallRepository.clearLocal(local)
            }

            override fun insertLocal(remote: OneCallModelRemote) {
                insertLocal(
                    remote = remote,
                    coordinates = coordinates
                )
            }

            override fun getLocal(): Flow<OneCallModelLocal?> {
                return getLocal(
                    coordinates = coordinates
                )
            }

            override suspend fun getRemote(): OneCallModelRemote {
                return getRemote(
                    coordinates = coordinates
                )
            }

        }()

    }

    private suspend fun getRemote(
        coordinates: NBCoordinatesModel
    ): OneCallModelRemote {
        return oneCallService.getOneCall(
            latitude = coordinates.latitude,
            longitude = coordinates.longitude,
            exclude = ConstantsCoreRemote.Query.Exclude.VALUE,
            units = ConstantsCoreRemote.Query.Units.VALUE,
            language = ConstantsCoreRemote.Query.Language.VALUE
        )
    }

    private fun getLocal(
        coordinates: NBCoordinatesModel
    ): Flow<OneCallModelLocal?> {
        return oneCallDao.getOneCall(
            latitude = coordinates.latitude,
            longitude = coordinates.longitude
        )
    }

    private fun clearLocal(
        local: OneCallModelLocal
    ) {
        val metadataId = local.metadata.id
        oneCallDao.deleteOneCallMetadata(metadataId)
        currentWeatherDao.deleteCurrentWeather(metadataId)
        minutelyForecastDao.deleteMinutelyForecasts(metadataId)
        hourlyForecastDao.deleteHourlyForecasts(metadataId)
        dailyForecastDao.deleteDailyForecasts(metadataId)
        nationalWeatherAlertDao.deleteNationalWeatherAlerts(metadataId)
    }

    private fun insertLocal(
        remote: OneCallModelRemote,
        coordinates: NBCoordinatesModel
    ) {
        val oneCallMetadata = OneCallModelData.remoteToLocal(
            remote = remote,
            latitude = coordinates.latitude,
            longitude = coordinates.longitude
        )

        val metadataId = oneCallDao.insertOneCallMetadata(oneCallMetadata)

        val currentWeather = CurrentWeatherModelData.remoteToLocal(
            remote.current, metadataId
        )
        currentWeatherDao.insertCurrentWeather(currentWeather)

        val minutelyForecasts = MinutelyForecastModelData.remoteToLocal(
            remote.minutely, metadataId
        )
        minutelyForecastDao.insertMinutelyForecasts(minutelyForecasts)

        val hourlyForecasts = HourlyForecastModelData.remoteToLocal(
            remote.hourly, metadataId
        )
        hourlyForecastDao.insertHourlyForecasts(hourlyForecasts)

        val dailyForecasts = DailyForecastModelData.remoteToLocal(
            remote.daily, metadataId
        )
        dailyForecastDao.insertDailyForecasts(dailyForecasts)

        val nationalWeatherAlerts = NationalWeatherAlertModelData.remoteToLocal(
            remote.alerts, metadataId
        )
        nationalWeatherAlertDao.insertNationalWeatherAlerts(nationalWeatherAlerts)
    }

}