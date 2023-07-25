package de.niklasbednarczyk.nbweather.data.geocoding.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote

data class LocationModelData(
    private val localNames: LocalNamesModelData,
    private val name: String?,
    private val state: String?,
    private val country: String?,
    val latitude: Double,
    val longitude: Double
) {

    val localizedName: NBString?
        get() = NBString.Value.from(localNames.localizedName ?: name)

    val localizedNameAndCountry: NBString?
        get() {
            return when {
                localizedName != null && country != null -> NBString.Resource(
                    R.string.format_comma_2_items,
                    localizedName,
                    country
                )

                localizedName != null && country == null -> localizedName

                else -> NBString.Value.from(null)
            }
        }

    val stateAndCountry: NBString?
        get() = when {
            state != null && country != null -> NBString.Resource(
                R.string.format_comma_2_items,
                state,
                country
            )

            state == null && country != null -> NBString.Value.from(country)
            else -> NBString.Value.from(null)
        }

    companion object {

        internal fun remoteToData(
            remote: LocationModelRemote?
        ): LocationModelData? {
            return nbNullSafe(remote) { model ->
                LocationModelData(
                    localNames = LocalNamesModelData.remoteToData(model.localNames),
                    name = model.name,
                    state = model.state,
                    country = model.country,
                    latitude = model.lat,
                    longitude = model.lon
                )
            }
        }

        internal fun remoteListToData(
            remoteList: List<LocationModelRemote>
        ): List<LocationModelData> {
            return remoteList.mapNotNull { remote ->
                remoteToData(remote)
            }
        }

        internal fun remoteToLocal(
            remote: LocationModelRemote?,
            latitude: Double,
            longitude: Double,
            lastVisitedTimestampEpochSeconds: Long? = null
        ): LocationModelLocal? {
            return nbNullSafe(remote) { model ->
                LocationModelLocal(
                    name = model.name,
                    localNames = LocalNamesModelData.remoteToLocal(model.localNames),
                    country = model.country,
                    state = model.state,
                    latitude = latitude,
                    longitude = longitude,
                    lastVisitedTimestampEpochSeconds = lastVisitedTimestampEpochSeconds
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
        ): LocationModelData? {
            return nbNullSafe(local) { model ->
                LocationModelData(
                    localNames = LocalNamesModelData.localToData(model.localNames),
                    name = model.name,
                    state = model.state,
                    country = model.country,
                    latitude = model.latitude,
                    longitude = model.longitude
                )
            }
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