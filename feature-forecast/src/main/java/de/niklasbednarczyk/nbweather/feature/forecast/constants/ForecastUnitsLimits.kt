package de.niklasbednarczyk.nbweather.feature.forecast.constants

import de.niklasbednarczyk.nbweather.feature.forecast.models.limits.ForecastLimitValue
import de.niklasbednarczyk.nbweather.feature.forecast.models.limits.ForecastUnitsLimitsItem

object ForecastUnitsLimits {

    object Distance : ForecastUnitsLimitsItem {
        // meter
        override val min = null
        override val max = null
    }

    object Percent : ForecastUnitsLimitsItem {
        override val min = ForecastLimitValue(0.0)
        override val max = ForecastLimitValue(100.0)
    }

    object Precipitation : ForecastUnitsLimitsItem {
        // millimeter
        override val min = ForecastLimitValue(0.0)
        override val max = ForecastLimitValue(10.0)
    }

    object Pressure : ForecastUnitsLimitsItem {
        // hectopascal
        override val min = null
        override val max = null
    }

    object Probability : ForecastUnitsLimitsItem {
        // decimal
        override val min = ForecastLimitValue(0.0)
        override val max = ForecastLimitValue(1.0)
    }

    object Temperature : ForecastUnitsLimitsItem {
        // kelvin
        override val min = ForecastLimitValue(273.15)
        override val max = null
    }

    object UVIndex : ForecastUnitsLimitsItem {
        override val min = ForecastLimitValue(0.0)
        override val max = ForecastLimitValue(11.0)
    }

    object WindSpeed : ForecastUnitsLimitsItem {
        // meter per second
        override val min = ForecastLimitValue(0.0)
        override val max = null
    }

}