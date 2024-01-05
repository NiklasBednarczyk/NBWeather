package de.niklasbednarczyk.nbweather.data.geocoding.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbMapNotNull
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
                localizedName != null && country != null -> NBString.ResString(
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
            state != null && country != null -> NBString.ResString(
                R.string.format_comma_2_items,
                state,
                country
            )

            state == null && country != null -> NBString.Value.from(country)
            else -> NBString.Value.from(null)
        }

    internal companion object {

        fun remoteToData(
            remote: LocationModelRemote?
        ): LocationModelData? {
            return nbNullSafe(remote) { r ->
                LocationModelData(
                    localNames = LocalNamesModelData.remoteToData(r.localNames),
                    name = r.name,
                    state = r.state,
                    country = r.country,
                    latitude = r.lat,
                    longitude = r.lon
                )
            }
        }

        fun remoteListToData(
            remoteList: List<LocationModelRemote>
        ): List<LocationModelData> {
            return remoteList.mapNotNull { remote ->
                remoteToData(remote)
            }
        }

        fun remoteToLocal(
            remote: LocationModelRemote?,
            latitude: Double,
            longitude: Double,
            lastVisitedTimestampEpochSeconds: Long? = null,
            order: Long? = null,
        ): LocationModelLocal? {
            return nbNullSafe(remote) { r ->
                LocationModelLocal(
                    name = r.name,
                    localNames = LocalNamesModelData.remoteToLocal(r.localNames),
                    country = r.country,
                    state = r.state,
                    latitude = latitude,
                    longitude = longitude,
                    lastVisitedTimestampEpochSeconds = lastVisitedTimestampEpochSeconds,
                    order = order
                )
            }
        }

        fun remoteListToLocal(remoteList: List<LocationModelRemote>?): List<LocationModelLocal> {
            return remoteList.nbMapNotNull { remote ->
                remoteToLocal(remote, remote.lat, remote.lon)
            }
        }

        fun localToData(
            local: LocationModelLocal?,
        ): LocationModelData? {
            return nbNullSafe(local) { l ->
                LocationModelData(
                    localNames = LocalNamesModelData.localToData(l.localNames),
                    name = l.name,
                    state = l.state,
                    country = l.country,
                    latitude = l.latitude,
                    longitude = l.longitude
                )
            }
        }

        fun localListToData(
            localList: List<LocationModelLocal>?
        ): List<LocationModelData>? {
            return localList?.mapNotNull { local ->
                localToData(local)
            }

        }
    }

}