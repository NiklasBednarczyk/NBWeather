package de.niklasbednarczyk.openweathermap.data.geocoding.models

import de.niklasbednarczyk.openweathermap.core.common.language.LanguageType
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.openweathermap.data.geocoding.values.CoordinateValue

data class LocationModelData(
    private val name: String?,
    private val localNames: LocalNamesModelData?,
    private val country: String?,
    private val state: String?,
    val latitude: CoordinateValue,
    val longitude: CoordinateValue
) {

    val localizedName: String?
        get() {
            val localName = when (LanguageType.fromLocale()) {
                LanguageType.DE -> localNames?.de
                LanguageType.EN -> localNames?.en
            }
            return localName ?: name
        }

    val stateAndCountry: String?
        get() {
            return when {
                state != null && country != null -> "$state, $country"
                state == null && country != null -> country
                else -> null
            }
        }

    val localizedNameAndCountry: String?
        get() {
            if (country == null || localizedName == null) return null
            return "$localizedName, $country"
        }

    companion object {


        internal fun remoteToData(remoteList: List<LocationModelRemote>): List<LocationModelData> {
            return remoteList.map { remote ->
                LocationModelData(
                    name = remote.name,
                    localNames = LocalNamesModelData.remoteToData(remote.localNames),
                    country = remote.country,
                    state = remote.state,
                    latitude = CoordinateValue(remote.lat),
                    longitude = CoordinateValue(remote.lon),
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

        internal fun localToData(
            local: LocationModelLocal?
        ): LocationModelData? {
            if (local == null) return null
            return LocationModelData(
                name = local.name,
                localNames = LocalNamesModelData.localToData(local.localNames),
                country = local.country,
                state = local.state,
                latitude = CoordinateValue(local.latitude),
                longitude = CoordinateValue(local.longitude)
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