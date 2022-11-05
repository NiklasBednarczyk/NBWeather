package de.niklasbednarczyk.openweathermap.data.geocoding.models

import de.niklasbednarczyk.openweathermap.core.common.display.OwmDataLanguageType
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.data.geocoding.R
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocationModelRemote

data class LocationModelData(
    val localizedName: OwmString?,
    val stateAndCountry: OwmString?,
    val localizedNameAndCountry: OwmString?,
    val latitude: Double,
    val longitude: Double
) {

    companion object {

        private fun toData(
            name: String?,
            localName: LocalNameModelData,
            country: String?,
            state: String?,
            latitude: Double,
            longitude: Double
        ): LocationModelData {
            val localizedNameValue = localName.value ?: name
            val stateAndCountry = when {
                state != null && country != null -> OwmString.Resource(
                    R.string.format_comma,
                    state,
                    country
                )
                state == null && country != null -> OwmString.Value.from(country)
                else -> OwmString.Value.from(null)
            }
            val localizedNameAndCountry = when {
                localizedNameValue != null && country != null -> OwmString.Resource(
                    R.string.format_comma,
                    localizedNameValue,
                    country
                )
                localizedNameValue != null && country == null -> OwmString.Value.from(
                    localizedNameValue
                )
                else -> OwmString.Value.from(null)
            }

            return LocationModelData(
                localizedName = OwmString.Value.from(localizedNameValue),
                stateAndCountry = stateAndCountry,
                localizedNameAndCountry = localizedNameAndCountry,
                latitude = latitude,
                longitude = longitude
            )

        }

        internal fun remoteToData(
            remoteList: List<LocationModelRemote>,
            dataLanguage: OwmDataLanguageType
        ): List<LocationModelData> {
            return remoteList.map { remote ->
                toData(
                    name = remote.name,
                    localName = LocalNameModelData.remoteToData(remote.localNames, dataLanguage),
                    country = remote.country,
                    state = remote.state,
                    latitude = remote.lat,
                    longitude = remote.lon
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
                localNames = LocalNameModelData.remoteToLocal(remote.localNames),
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
            local: LocationModelLocal?,
            dataLanguage: OwmDataLanguageType
        ): LocationModelData? {
            if (local == null) return null
            return toData(
                name = local.name,
                localName = LocalNameModelData.localToData(local.localNames, dataLanguage),
                country = local.country,
                state = local.state,
                latitude = local.latitude,
                longitude = local.longitude
            )
        }

        internal fun localListToData(
            localList: List<LocationModelLocal>?,
            dataLanguage: OwmDataLanguageType
        ): List<LocationModelData>? {
            return localList?.mapNotNull { local ->
                localToData(local, dataLanguage)
            }

        }
    }

}