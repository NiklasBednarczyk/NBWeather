package de.niklasbednarczyk.nbweather.feature.forecast.constants

import de.niklasbednarczyk.nbweather.core.ui.limit.NBLimitValue
import de.niklasbednarczyk.nbweather.core.ui.limit.NBLimitsItem

object ConstantsFeatureForecast {

    object Limits {

        object Distance : NBLimitsItem {
            // meter
            override val min = null
            override val max = null
        }

        object Percent : NBLimitsItem {
            override val min = NBLimitValue(0.0)
            override val max = NBLimitValue(100.0)
        }

        object Precipitation : NBLimitsItem {
            // millimeter
            override val min = NBLimitValue(0.0)
            override val max = NBLimitValue(10.0)
        }

        object Pressure : NBLimitsItem {
            // hectopascal
            override val min = null
            override val max = null
        }

        object Probability : NBLimitsItem {
            // decimal
            override val min = NBLimitValue(0.0)
            override val max = NBLimitValue(1.0)
        }

        object Temperature : NBLimitsItem {
            // kelvin
            override val min = NBLimitValue(273.15)
            override val max = null
        }

        object UVIndex : NBLimitsItem {
            override val min = NBLimitValue(0.0)
            override val max = NBLimitValue(11.0)
        }

        object WindSpeed : NBLimitsItem {
            // meter per second
            override val min = NBLimitValue(0.0)
            override val max = null
        }

    }

}