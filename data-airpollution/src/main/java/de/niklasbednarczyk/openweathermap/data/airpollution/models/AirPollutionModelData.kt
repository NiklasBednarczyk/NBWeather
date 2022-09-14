package de.niklasbednarczyk.openweathermap.data.airpollution.models

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.values.DateTimeValue
import de.niklasbednarczyk.openweathermap.data.airpollution.local.models.AirPollutionEntityLocal
import de.niklasbednarczyk.openweathermap.data.airpollution.remote.models.AirPollutionModelRemote
import de.niklasbednarczyk.openweathermap.data.airpollution.values.AirQualityIndexValue

data class AirPollutionModelData(
    val forecastTime: DateTimeValue,
    val airQualityIndex: AirQualityIndexValue,
    val components: ComponentsModelData
) {

    companion object {

        internal fun remoteToLocal(
            remoteList: List<AirPollutionModelRemote>?,
            metadataId: Long,
        ): List<AirPollutionEntityLocal> {
            return remoteList?.map { remote ->
                AirPollutionEntityLocal(
                    metadataId = metadataId,
                    dt = remote.dt,
                    aqi = remote.main?.aqi,
                    components = ComponentsModelData.remoteToLocal(remote.components)
                )
            } ?: emptyList()
        }

        internal fun localToData(
            localList: List<AirPollutionEntityLocal>?
        ): List<AirPollutionModelData> {
            return localList?.map { local ->
                AirPollutionModelData(
                    forecastTime = DateTimeValue(local.dt),
                    airQualityIndex = AirQualityIndexValue(local.aqi),
                    components = ComponentsModelData.localToData(local.components)
                )
            } ?: emptyList()
        }

    }

}