package de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.daily

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue
import kotlin.math.abs
import kotlin.math.max

data class LocationOverviewDailyTemperatureModel(
    val value: TemperatureValue?,
    private val prefix: NBString?,
    val factor: Float
) {

    val valueWithPrefix: NBString
        @Composable
        get() = NBString.Resource(
            R.string.format_space_2_items,
            prefix,
            value?.getShort()?.getDisplayValueWithSymbol(NBSettings.units)
        )

    companion object {

        private const val MIN_WEIGHT = 0.00001f

        fun from(
            temperatureCurrentValue: TemperatureValue?,
            temperatureCurrentRounded: Double,
            temperatureValueAll: Double,
            temperatureDifferenceAll: Double,
            @StringRes prefixResId: Int
        ): LocationOverviewDailyTemperatureModel {
            val temperatureDifferenceCurrentAll =
                abs(temperatureValueAll.minus(temperatureCurrentRounded))

            val factorCalculated =
                temperatureDifferenceCurrentAll.div(temperatureDifferenceAll).toFloat()
            val factor = max(MIN_WEIGHT, factorCalculated)

            return LocationOverviewDailyTemperatureModel(
                value = temperatureCurrentValue,
                factor = factor,
                prefix = NBString.Resource(prefixResId)
            )
        }

    }


}