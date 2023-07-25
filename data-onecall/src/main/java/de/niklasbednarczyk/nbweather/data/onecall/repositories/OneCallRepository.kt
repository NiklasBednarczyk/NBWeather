package de.niklasbednarczyk.nbweather.data.onecall.repositories

import android.content.Context
import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.LocalRemoteOfflineMediator
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
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallMetadataEntityLocal
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
        latitude: Double,
        longitude: Double,
        forceUpdate: Boolean
    ): Flow<NBResource<OneCallModelData>> {
        return object :
            LocalRemoteOfflineMediator<OneCallModelData, OneCallModelLocal, OneCallModelRemote>() {
            override fun getLocal(): Flow<OneCallModelLocal?> {
                return oneCallDao.getOneCall(latitude, longitude)
            }

            override suspend fun getRemote(): OneCallModelRemote {
                return oneCallService.getOneCall(
                    latitude = latitude,
                    longitude = longitude,
                    exclude = ConstantsCoreRemote.Query.Exclude.VALUE,
                    language = ConstantsCoreRemote.Query.Language.VALUE,
                    units = ConstantsCoreRemote.Query.Units.VALUE
                )
            }


            override fun localToData(local: OneCallModelLocal): OneCallModelData {
                return OneCallModelData.localToData(local)
            }

            override fun shouldGetRemote(local: OneCallModelLocal): Boolean {
                return forceUpdate || local.metadata.isExpired
            }

            override fun clearLocal(local: OneCallModelLocal) {
                val metadataId = local.metadata.id
                oneCallDao.deleteOneCall(local.metadata.latitude, local.metadata.longitude)
                currentWeatherDao.deleteCurrentWeather(metadataId)
                minutelyForecastDao.deleteMinutelyForecasts(metadataId)
                hourlyForecastDao.deleteHourlyForecasts(metadataId)
                dailyForecastDao.deleteDailyForecasts(metadataId)
                nationalWeatherAlertDao.deleteNationalWeatherAlerts(metadataId)
            }

            override fun insertLocal(remote: OneCallModelRemote) {
                val oneCallMetadata = OneCallMetadataEntityLocal(
                    latitude = latitude,
                    longitude = longitude,
                    timezone = remote.timezone,
                    timezoneOffset = remote.timezoneOffset
                )

                val metadataId = oneCallDao.insertOneCall(oneCallMetadata)

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
        }()
    }

}