package de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.daily

import androidx.annotation.StringRes
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue
import kotlin.math.abs
import kotlin.math.max

data class LocationOverviewDailyTemperatureModel(
    val text: NBString?,
    val prefix: NBString?,
    val factor: Float
) {

    companion object {

        private const val MIN_WEIGHT = 0.00001f

        fun from(
            temperatureCurrent: TemperatureValue?,
            temperatureValueAll: Double,
            temperatureDifferenceAll: Double,
            @StringRes prefixResId: Int
        ): LocationOverviewDailyTemperatureModel? {
            if (temperatureCurrent == null) return null

            val temperatureValueCurrent = temperatureCurrent.roundedValue ?: return null

            val temperatureDifferenceCurrentAll =
                abs(temperatureValueAll.minus(temperatureValueCurrent))

            val factorCalculated =
                temperatureDifferenceCurrentAll.div(temperatureDifferenceAll).toFloat()
            val factor = max(MIN_WEIGHT, factorCalculated)

            return LocationOverviewDailyTemperatureModel(
                text = temperatureCurrent.displayValueWithShortUnit,
                factor = factor,
                prefix = NBString.Resource(prefixResId)
            )
        }

    }


}