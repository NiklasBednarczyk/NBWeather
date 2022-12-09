package de.niklasbednarczyk.openweathermap.data.onecall.repositories

import de.niklasbednarczyk.openweathermap.core.common.data.OwmLanguageType
import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.LocalMediator
import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.LocalRemoteOfflineMediator
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.core.data.localremote.remote.extensions.remoteName
import de.niklasbednarczyk.openweathermap.data.onecall.local.daos.*
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.OneCallModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.models.*
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.OneCallModelRemote
import de.niklasbednarczyk.openweathermap.data.onecall.remote.services.OneCallService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OneCallRepository @Inject constructor(
    private val oneCallService: OneCallService,
    private val currentWeatherDao: CurrentWeatherDao,
    private val dailyForecastDao: DailyForecastDao,
    private val hourlyForecastDao: HourlyForecastDao,
    private val minutelyForecastDao: MinutelyForecastDao,
    private val nationalWeatherAlertDao: NationalWeatherAlertDao,
    private val oneCallDao: OneCallDao
) {

    suspend fun getOneCall(
        latitude: Double,
        longitude: Double,
        language: OwmLanguageType,
        units: OwmUnitsType,
        forceUpdate: Boolean = false
    ): Flow<OwmResource<OneCallModelData>> {
        return object :
            LocalRemoteOfflineMediator<OneCallModelData, OneCallModelLocal, OneCallModelRemote>() {
            override fun getLocal(): Flow<OneCallModelLocal?> {
                return oneCallDao.getOneCall(latitude, longitude)
            }

            override suspend fun getRemote(): OneCallModelRemote {
                return oneCallService.getOneCall(
                    latitude,
                    longitude,
                    language.remoteName,
                    units.remoteName
                )
            }


            override fun localToData(local: OneCallModelLocal): OneCallModelData {
                return OneCallModelData.localToData(local)
            }

            override fun shouldGetRemote(local: OneCallModelLocal): Boolean {
                return forceUpdate || local.metadata.isExpired || local.metadata.language != language || local.metadata.units != units
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
                val oneCallMetadata = OneCallMetadataModelData.remoteToLocal(
                    remote, latitude, longitude, language, units
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

    fun getOneCallLocal(
        latitude: Double,
        longitude: Double
    ): Flow<OwmResource<OneCallModelData?>> {
        return object :
            LocalMediator<OneCallModelData, OneCallModelLocal>() {
            override fun getLocal(): Flow<OneCallModelLocal?> {
                return oneCallDao.getOneCall(latitude, longitude)
            }

            override fun localToData(local: OneCallModelLocal): OneCallModelData {
                return OneCallModelData.localToData(local)
            }
        }()
    }

}