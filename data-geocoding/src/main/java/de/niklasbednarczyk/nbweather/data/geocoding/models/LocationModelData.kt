package de.niklasbednarczyk.nbweather.data.geocoding.models

import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote

data class LocationModelData(
    val localizedName: NBString?,
    val stateAndCountry: NBString?,
    val localizedNameAndCountry: NBString?,
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
                state != null && country != null -> NBString.Resource(
                    R.string.format_comma,
                    state,
                    country
                )
                state == null && country != null -> NBString.Value.from(country)
                else -> NBString.Value.from(null)
            }
            val localizedNameAndCountry = when {
                localizedNameValue != null && country != null -> NBString.Resource(
                    R.string.format_comma,
                    localizedNameValue,
                    country
                )
                localizedNameValue != null && country == null -> NBString.Value.from(
                    localizedNameValue
                )
                else -> NBString.Value.from(null)
            }

            return LocationModelData(
                localizedName = NBString.Value.from(localizedNameValue),
                stateAndCountry = stateAndCountry,
                localizedNameAndCountry = localizedNameAndCountry,
                latitude = latitude,
                longitude = longitude
            )

        }

        internal fun remoteToData(
            remoteList: List<LocationModelRemote>,
            language: NBLanguageType
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
            return nbNullSafe(remote) { model ->
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
            language: NBLanguageType
        ): LocationModelData? {
            return nbNullSafe(local) { model ->
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
            language: NBLanguageType
        ): List<LocationModelData>? {
            return localList?.mapNotNull { local ->
                localToData(local, language)
            }

        }
    }

}