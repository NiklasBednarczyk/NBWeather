package de.niklasbednarczyk.nbweather.data.airpollution.repositories

import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.LocalRemoteOfflineMediator
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.data.airpollution.local.daos.NBAirPollutionDao
import de.niklasbednarczyk.nbweather.data.airpollution.local.daos.NBAirPollutionItemDao
import de.niklasbednarczyk.nbweather.data.airpollution.local.models.AirPollutionMetadataEntityLocal
import de.niklasbednarczyk.nbweather.data.airpollution.local.models.AirPollutionModelLocal
import de.niklasbednarczyk.nbweather.data.airpollution.models.AirPollutionItemModelData
import de.niklasbednarczyk.nbweather.data.airpollution.models.AirPollutionModelData
import de.niklasbednarczyk.nbweather.data.airpollution.remote.models.AirPollutionModelRemote
import de.niklasbednarczyk.nbweather.data.airpollution.remote.services.NBAirPollutionService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirPollutionRepository @Inject constructor(
    private val airPollutionService: NBAirPollutionService,
    private val airPollutionDao: NBAirPollutionDao,
    private val airPollutionItemDao: NBAirPollutionItemDao
) {

    companion object {

        //TODO REDESIGN: Create fake repository

    }

    suspend fun getAirPollution(
        latitude: Double,
        longitude: Double
    ): Flow<NBResource<AirPollutionModelData>> {
        return object : LocalRemoteOfflineMediator<AirPollutionModelData, AirPollutionModelLocal, AirPollutionModelRemote>() {

            override fun getLocal(): Flow<AirPollutionModelLocal?> {
                return airPollutionDao.getAirPollution(latitude, longitude)
            }

            override suspend fun getRemote(): AirPollutionModelRemote {
                return airPollutionService.getAirPollution(
                    latitude = latitude,
                    longitude = longitude
                )
            }

            override fun localToData(local: AirPollutionModelLocal): AirPollutionModelData {
                return AirPollutionModelData.localToData(local)
            }

            override fun shouldGetRemote(local: AirPollutionModelLocal): Boolean {
                return local.metadata.isExpired
            }

            override fun clearLocal(local: AirPollutionModelLocal) {
                val metadataId = local.metadata.id
                airPollutionDao.deleteAirPollution(local.metadata.latitude, local.metadata.longitude)
                airPollutionItemDao.deleteAirPollutionItems(metadataId)
            }

            override fun insertLocal(remote: AirPollutionModelRemote) {
                val airPollutionMetadata = AirPollutionMetadataEntityLocal(
                    latitude = latitude,
                    longitude = longitude
                )

                val metadataId = airPollutionDao.insertAirPollution(airPollutionMetadata)

                val airPollutionItems = AirPollutionItemModelData.remoteToLocal(
                    remote.list, metadataId
                )
                airPollutionItemDao.insertAirPollutionItems(airPollutionItems)
            }

        }()




    }


}