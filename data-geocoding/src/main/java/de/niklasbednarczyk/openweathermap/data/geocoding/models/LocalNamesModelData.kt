package de.niklasbednarczyk.openweathermap.data.geocoding.models

import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocalNamesModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocalNamesModelRemote

data class LocalNamesModelData(
    val de: String?,
    val en: String?
) {

    companion object {

        internal fun remoteToData(remote: LocalNamesModelRemote?): LocalNamesModelData? {
            if (remote == null) return null
            return LocalNamesModelData(
                de = remote.de,
                en = remote.en
            )
        }

        internal fun remoteToLocal(remote: LocalNamesModelRemote?): LocalNamesModelLocal? {
            if (remote == null) return null
            return LocalNamesModelLocal(
                de = remote.de,
                en = remote.en
            )
        }

        internal fun localToData(local: LocalNamesModelLocal?): LocalNamesModelData? {
            if (local == null) return null
            return LocalNamesModelData(
                de = local.de,
                en = local.en
            )
        }

    }


}