package de.niklasbednarczyk.nbweather.data.airpollution.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbMap
import de.niklasbednarczyk.nbweather.data.airpollution.local.models.AirPollutionItemEntityLocal
import de.niklasbednarczyk.nbweather.data.airpollution.local.models.MainModelLocal
import de.niklasbednarczyk.nbweather.data.airpollution.remote.models.AirPollutionItemModelRemote
import de.niklasbednarczyk.nbweather.data.airpollution.values.aqi.AirQualityIndexValue

data class AirPollutionItemModelData(
    val forecastTime: NBDateTimeValue?,
    val airQualityIndex: AirQualityIndexValue?,
    val components: ComponentsModelData?
) {

    internal companion object {

        fun remoteToLocal(
            remoteList: List<AirPollutionItemModelRemote>?,
            metadataId: Long
        ): List<AirPollutionItemEntityLocal> {
            return remoteList.nbMap { remote ->
                AirPollutionItemEntityLocal(
                    metadataId = metadataId,
                    dt = remote.dt,
                    main = MainModelLocal(
                        aqi = remote.main?.aqi
                    ),
                    components = ComponentsModelData.remoteToLocal(remote.components)
                )
            }
        }

        fun localToData(
            localList: List<AirPollutionItemEntityLocal>?
        ): List<AirPollutionItemModelData> {
            return localList.nbMap { local ->
                AirPollutionItemModelData(
                    forecastTime = NBDateTimeValue.from(local.dt),
                    airQualityIndex = AirQualityIndexValue.from(local.main?.aqi),
                    components = ComponentsModelData.localToData(local.components)
                )
            }
        }

    }

}