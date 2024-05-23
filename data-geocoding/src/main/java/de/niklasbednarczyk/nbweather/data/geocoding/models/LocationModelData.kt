package de.niklasbednarczyk.nbweather.data.geocoding.models

import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal.Companion.coordinates
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote.Companion.coordinates

data class LocationModelData(
    val coordinates: NBCoordinatesModel,
    val country: String?,
    val state: String?,
    internal val name: String?,
    internal val localNames: LocalNamesModelData?,
    internal val lastVisitedTimestampEpochSeconds: Long?,
    internal val order: Long?
) {

    val localizedName: NBString?
        get() = NBString.Value.from(localNames?.localizedName ?: name)

    val localizedNameAndCountry: NBString?
        get() {
            return when {
                localizedName != null && country != null -> NBString.ResString(
                    R.string.format_comma_2_items,
                    localizedName,
                    country
                )

                localizedName != null && country == null -> localizedName

                else -> null
            }
        }

    internal companion object {

        fun dataToLocal(
            data: LocationModelData
        ): LocationModelLocal {
            return LocationModelLocal(
                latitude = data.coordinates.latitude,
                longitude = data.coordinates.longitude,
                name = data.name,
                localNames = LocalNamesModelData.dataToLocal(data.localNames),
                country = data.country,
                state = data.state,
                lastVisitedTimestampEpochSeconds = data.lastVisitedTimestampEpochSeconds,
                order = data.order
            )
        }

        fun localToData(
            local: LocationModelLocal
        ): LocationModelData {
            return LocationModelData(
                coordinates = local.coordinates,
                name = local.name,
                localNames = LocalNamesModelData.localToData(local.localNames),
                country = local.country,
                state = local.state,
                lastVisitedTimestampEpochSeconds = local.lastVisitedTimestampEpochSeconds,
                order = local.order
            )
        }

        fun localToDataNullable(
            local: LocationModelLocal?
        ): LocationModelData? {
            return nbNullSafe(local) { l ->
                localToData(
                    local = l
                )
            }
        }

        fun remoteToData(
            remote: LocationModelRemote?
        ): LocationModelData? {
            return nbNullSafe(remote) { r ->
                LocationModelData(
                    coordinates = r.coordinates,
                    name = r.name,
                    localNames = LocalNamesModelData.remoteToData(r.localNames),
                    country = r.country,
                    state = r.state,
                    lastVisitedTimestampEpochSeconds = null,
                    order = null
                )
            }
        }

        fun remoteToLocal(
            remote: LocationModelRemote
        ): LocationModelLocal {
            return LocationModelLocal(
                latitude = remote.lat,
                longitude = remote.lon,
                name = remote.name,
                localNames = LocalNamesModelData.remoteToLocal(remote.localNames),
                country = remote.country,
                state = remote.state,
                lastVisitedTimestampEpochSeconds = null,
                order = null
            )
        }

    }

}