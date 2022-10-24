package de.niklasbednarczyk.openweathermap.data.geocoding.models

import de.niklasbednarczyk.openweathermap.core.common.language.OwmLanguageType
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocationModelRemote

data class LocationModelData(
    private val name: String?,
    private val localNames: LocalNamesModelData?,
    private val country: String?,
    private val state: String?,
    val latitude: Double,
    val longitude: Double
) {

    val localizedName: OwmString.Value?
        get() {
            val localName = when (OwmLanguageType.fromLocale()) {
                OwmLanguageType.DE -> localNames?.de
                OwmLanguageType.EN -> localNames?.en
            }
            val value = localName ?: name
            return OwmString.Value.from(value)
        }

    val stateAndCountry: OwmString.Value?
        get() {
            val value = when {
                state != null && country != null -> "$state, $country"
                state == null && country != null -> country
                else -> null
            }
            return OwmString.Value.from(value)
        }

    val localizedNameAndCountry: OwmString.Value?
        get() {
            val localName = localizedName
            val value = if (country == null || localName == null) {
                null
            } else {
                "${localName.value}, $country"
            }
            return OwmString.Value.from(value)
        }

    companion object {


        internal fun remoteToData(remoteList: List<LocationModelRemote>): List<LocationModelData> {
            return remoteList.map { remote ->
                LocationModelData(
                    name = remote.name,
                    localNames = LocalNamesModelData.remoteToData(remote.localNames),
                    country = remote.country,
                    state = remote.state,
                    latitude = remote.lat,
                    longitude = remote.lon,
                )
            }
        }

        internal fun remoteToLocal(
            remote: LocationModelRemote?,
            latitude: Double,
            longitude: Double
        ): LocationModelLocal? {
            if (remote == null) return null
            return LocationModelLocal(
                name = remote.name,
                localNames = LocalNamesModelData.remoteToLocal(remote.localNames),
                country = remote.country,
                state = remote.state,
                latitude = latitude,
                longitude = longitude,
            )
        }

        internal fun remoteListToLocal(remoteList: List<LocationModelRemote>?): List<LocationModelLocal> {
            return remoteList?.mapNotNull { remote ->
                remoteToLocal(remote, remote.lat, remote.lon)
            } ?: emptyList()
        }

        internal fun localToData(
            local: LocationModelLocal?
        ): LocationModelData? {
            if (local == null) return null
            return LocationModelData(
                name = local.name,
                localNames = LocalNamesModelData.localToData(local.localNames),
                country = local.country,
                state = local.state,
                latitude = local.latitude,
                longitude = local.longitude
            )
        }

        internal fun localListToData(
            localList: List<LocationModelLocal>?
        ): List<LocationModelData>? {
            return localList?.mapNotNull { local ->
                localToData(local)
            }

        }
    }

}