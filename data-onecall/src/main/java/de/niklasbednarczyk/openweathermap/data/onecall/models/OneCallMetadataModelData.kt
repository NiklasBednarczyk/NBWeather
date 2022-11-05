package de.niklasbednarczyk.openweathermap.data.onecall.models

import de.niklasbednarczyk.openweathermap.core.common.display.OwmDataLanguageType
import de.niklasbednarczyk.openweathermap.core.common.display.OwmUnitsType
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.OneCallMetadataEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.OneCallModelRemote

data class OneCallMetadataModelData(
    val timezoneOffset: Long? //TODO (#9) Make value class
) {

    companion object {

        internal fun remoteToLocal(
            remote: OneCallModelRemote,
            latitude: Double,
            longitude: Double,
            dataLanguage: OwmDataLanguageType,
            units: OwmUnitsType
        ): OneCallMetadataEntityLocal {
            return OneCallMetadataEntityLocal(
                dataLanguage = dataLanguage,
                units = units,
                latitude = latitude,
                longitude = longitude,
                timezone = remote.timezone,
                timezoneOffset = remote.timezoneOffset
            )
        }

        internal fun localToData(
            local: OneCallMetadataEntityLocal
        ): OneCallMetadataModelData {
            return OneCallMetadataModelData(
                timezoneOffset = local.timezoneOffset
            )
        }

    }


}