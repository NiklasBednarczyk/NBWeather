package de.niklasbednarczyk.openweathermap.data.geocoding.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmLanguageType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.data.localremote.R
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
            language: OwmLanguageType
        ): List<LocationModelData> {
            return remoteList.map { remote ->
                toData(
                    name = remote.name,
                    localName = LocalNameModelData.remoteToData(remote.localNames, language),
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
            return owmNullSafe(remote) { model ->
                LocationModelLocal(
                    name = model.name,
                    localNames = LocalNameModelData.remoteToLocal(model.localNames),
                    country = model.country,
                    state = model.state,
                    latitude = latitude,
                    longitude = longitude,
                )
            }
        }

        internal fun remoteListToLocal(remoteList: List<LocationModelRemote>?): List<LocationModelLocal> {
            return remoteList?.mapNotNull { remote ->
                remoteToLocal(remote, remote.lat, remote.lon)
            } ?: emptyList()
        }

        internal fun localToData(
            local: LocationModelLocal?,
            language: OwmLanguageType
        ): LocationModelData? {
            return owmNullSafe(local) { model ->
                toData(
                    name = model.name,
                    localName = LocalNameModelData.localToData(model.localNames, language),
                    country = model.country,
                    state = model.state,
                    latitude = model.latitude,
                    longitude = model.longitude
                )
            }
        }

        internal fun localListToData(
            localList: List<LocationModelLocal>?,
            language: OwmLanguageType
        ): List<LocationModelData>? {
            return localList?.mapNotNull { local ->
                localToData(local, language)
            }

        }
    }

}