package de.niklasbednarczyk.openweathermap.data.geocoding.models

import de.niklasbednarczyk.openweathermap.core.common.language.LanguageType
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocalNamesModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.openweathermap.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.openweathermap.data.geocoding.values.CoordinateValue

data class LocationModelData(
    val localizedName: String?,
    val country: String?,
    val state: String?,
    val latitude: CoordinateValue,
    val longitude: CoordinateValue
) {

    companion object {

        internal fun remoteToData(remoteList: List<LocationModelRemote>): List<LocationModelData> {
            return remoteList.map { remote ->
                val localNames = remote.localNames
                val localName = when (LanguageType.fromLocale()) {
                    LanguageType.DE -> localNames?.de
                    LanguageType.EN -> localNames?.en
                }

                val localizedName = localName ?: localNames?.en ?: remote.name

                LocationModelData(
                    localizedName = localizedName,
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

            val remoteLocalNames = remote.localNames
            val localLocalNames = if (remoteLocalNames != null) {
                LocalNamesModelLocal(
                    de = remoteLocalNames.de,
                    en = remoteLocalNames.en
                )
            } else {
                null
            }

            return LocationModelLocal(
                name = remote.name,
                localNames = localLocalNames,
                country = remote.country,
                state = remote.state,
                latitude = latitude,
                longitude = longitude,
            )
        }

        internal fun localToData(
            local: LocationModelLocal
        ): LocationModelData {
            val localNames = local.localNames

            val localName = when (LanguageType.fromLocale()) {
                LanguageType.DE -> localNames?.de
                LanguageType.EN -> localNames?.en
            }

            val localizedName = localName ?: localNames?.en ?: local.name

            return LocationModelData(
                localizedName = localizedName,
                country = local.country,
                state = local.state,
                latitude = CoordinateValue(local.latitude),
                longitude = CoordinateValue(local.longitude)
            )
        }

    }

}