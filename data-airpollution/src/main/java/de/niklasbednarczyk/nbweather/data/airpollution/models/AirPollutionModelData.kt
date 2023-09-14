package de.niklasbednarczyk.nbweather.data.airpollution.models

import de.niklasbednarczyk.nbweather.data.airpollution.local.models.AirPollutionModelLocal

data class AirPollutionModelData(
    val airPollutionItems: List<AirPollutionItemModelData>
) {

    val current = airPollutionItems.firstOrNull()

    internal companion object {

        fun localToData(
            local: AirPollutionModelLocal
        ): AirPollutionModelData {
            return AirPollutionModelData(
                airPollutionItems = AirPollutionItemModelData.localToData(local.airPollutionItems)
            )
        }

    }

}