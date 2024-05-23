package de.niklasbednarczyk.nbweather.feature.search.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.common.locale.NBCountryType
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.common.flag
import de.niklasbednarczyk.nbweather.core.ui.common.fullName
import de.niklasbednarczyk.nbweather.core.ui.image.NBImageItem
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData

data class SearchOverviewLocationModel(
    val coordinates: NBCoordinatesModel,
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
            locations: List<LocationModelData>
        ): List<SearchOverviewLocationModel> {
            return locations.map { location ->
                SearchOverviewLocationModel(
                    coordinates = location.coordinates,
                    localizedName = location.localizedName,
                    country = location.country,
                    state = location.state
                )
            }
        }

    }

}