package de.niklasbednarczyk.openweathermap.data.onecall.models

import de.niklasbednarczyk.openweathermap.core.common.display.DataLanguageType
import de.niklasbednarczyk.openweathermap.core.common.display.UnitsType
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.OneCallMetadataEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.OneCallModelRemote

data class OneCallMetadataModelData(
    val units: UnitsType,
    val timezoneOffset: Long?
) {

    companion object {

        internal fun remoteToLocal(
            remote: OneCallModelRemote,
            units: UnitsType,
            language: DataLanguageType,
            latitude: Double,
            longitude: Double
        ): OneCallMetadataEntityLocal {
            return OneCallMetadataEntityLocal(
                units = units,
                language = language,
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
                units = local.units,
                timezoneOffset = local.timezoneOffset
            )
        }

    }


}