package de.niklasbednarczyk.openweathermap.data.airpollution.repositories

import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.LocalRemoteMediator
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.data.airpollution.local.daos.AirPollutionDao
import de.niklasbednarczyk.openweathermap.data.airpollution.local.daos.AirPollutionForecastDao
import de.niklasbednarczyk.openweathermap.data.airpollution.local.models.AirPollutionForecastMetadataEntityLocal
import de.niklasbednarczyk.openweathermap.data.airpollution.local.models.AirPollutionForecastModelLocal
import de.niklasbednarczyk.openweathermap.data.airpollution.models.AirPollutionModelData
import de.niklasbednarczyk.openweathermap.data.airpollution.remote.models.AirPollutionForecastModelRemote
import de.niklasbednarczyk.openweathermap.data.airpollution.remote.services.AirPollutionService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirPollutionRepository @Inject constructor(
    private val airPollutionService: AirPollutionService,
    private val airPollutionDao: AirPollutionDao,
    private val airPollutionForecastDao: AirPollutionForecastDao
) {

    suspend fun getAirPollutionForecast(
        latitude: Double,
        longitude: Double,
    ): Flow<Resource<List<AirPollutionModelData>>> {
        return object :
            LocalRemoteMediator<List<AirPollutionModelData>, AirPollutionForecastModelLocal, AirPollutionForecastModelRemote>() {
            override fun getLocal(): Flow<AirPollutionForecastModelLocal?> {
                return airPollutionForecastDao.getAirPollutionForecast(latitude, longitude)
            }

            override suspend fun getRemote(): AirPollutionForecastModelRemote {
                return airPollutionService.getAirPollutionForecast(latitude, longitude)
            }

            override fun clearLocal(local: AirPollutionForecastModelLocal) {
                val metadataId = local.metadata.id
                airPollutionForecastDao.deleteAirPollutionForecast(
                    local.metadata.latitude,
                    local.metadata.longitude
                )
                airPollutionDao.deleteAirPollutions(metadataId)
            }

            override fun insertLocal(remote: AirPollutionForecastModelRemote) {
                val airPollutionForecastMetadata = AirPollutionForecastMetadataEntityLocal(
                    latitude = latitude,
                    longitude = longitude
                )
                val metadataId =
                    airPollutionForecastDao.insertAirPollutionForecast(airPollutionForecastMetadata)

                val airPollutions = AirPollutionModelData.remoteToLocal(remote.list, metadataId)
                airPollutionDao.insertAirPollutions(airPollutions)
            }

            override fun localToData(local: AirPollutionForecastModelLocal): List<AirPollutionModelData> {
                return AirPollutionModelData.localToData(local.airPollutions)
            }

            override fun shouldGetRemote(local: AirPollutionForecastModelLocal): Boolean {
                return local.metadata.isExpired
            }


        }()
    }


}