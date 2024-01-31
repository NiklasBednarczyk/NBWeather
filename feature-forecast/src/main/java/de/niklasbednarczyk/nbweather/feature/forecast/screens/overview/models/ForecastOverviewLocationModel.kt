package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData

data class ForecastOverviewLocationModel(
    val latitude: Double,
    val longitude: Double,
    val title: NBString?
) {

    companion object {

        fun from(
            location: LocationModelData?
        ): ForecastOverviewLocationModel? {
            return nbNullSafe(location) { l ->
                ForecastOverviewLocationModel(
                    latitude = l.latitude,
                    longitude = l.longitude,
                    title = l.localizedNameAndCountry
                )
            }
        }

    }

}