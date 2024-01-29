package de.niklasbednarczyk.nbweather.feature.search.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.locale.NBCountryType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbMap
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.common.flag
import de.niklasbednarczyk.nbweather.core.ui.common.fullName
import de.niklasbednarczyk.nbweather.core.ui.image.NBImageItem
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData

data class SearchOverviewLocationModel(
    val latitude: Double,
    val longitude: Double,
    val localizedName: NBString?,
    private val country: String?,
    private val state: String?
) {

    private val countryType: NBCountryType?
        get() = NBCountryType.from(country)

    val flag: NBImageItem?
        get() = countryType?.flag

    val stateAndCountry: NBString?
        get() {
            val countryName = countryType?.fullName ?: NBString.Value.from(country)
            return when {
                state != null && countryName != null -> NBString.ResString(
                    R.string.format_comma_2_items,
                    state,
                    countryName
                )

                state == null && countryName != null -> countryName

                else -> null
            }
        }

    companion object {

        fun from(
            locations: List<LocationModelData>?
        ): List<SearchOverviewLocationModel> {
            return locations.nbMap { location ->
                SearchOverviewLocationModel(
                    latitude = location.latitude,
                    longitude = location.longitude,
                    localizedName = location.localizedName,
                    country = location.country,
                    state = location.state
                )
            }
        }

    }

}