package de.niklasbednarczyk.openweathermap.data.onecall.models

import de.niklasbednarczyk.openweathermap.core.common.language.LanguageType
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
            language: LanguageType
        ): OneCallMetadataEntityLocal {
            return OneCallMetadataEntityLocal(
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
                timezoneOffset = local.timezoneOffset
            )
        }

    }


}