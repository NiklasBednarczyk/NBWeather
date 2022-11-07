package de.niklasbednarczyk.openweathermap.data.onecall.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmLanguageType
import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.OneCallMetadataEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.OneCallModelRemote

data class OneCallMetadataModelData(
    val timezoneOffset: Long?,
    val units: OwmUnitsType
) {

    companion object {

        internal fun remoteToLocal(
            remote: OneCallModelRemote,
            latitude: Double,
            longitude: Double,
            language: OwmLanguageType,
            units: OwmUnitsType
        ): OneCallMetadataEntityLocal {
            return OneCallMetadataEntityLocal(
                language = language,
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
                timezoneOffset = local.timezoneOffset,
                units = local.units
            )
        }

    }


}