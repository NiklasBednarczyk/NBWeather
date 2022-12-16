package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallMetadataEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.OneCallModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.datetime.TimezoneOffsetValue

data class OneCallMetadataModelData(
    val timezoneOffset: TimezoneOffsetValue?,
    val units: NBUnitsType
) {

    companion object {

        internal fun remoteToLocal(
            remote: OneCallModelRemote,
            latitude: Double,
            longitude: Double,
            language: NBLanguageType,
            units: NBUnitsType
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
                timezoneOffset = TimezoneOffsetValue.from(local.timezoneOffset),
                units = local.units
            )
        }

    }


}