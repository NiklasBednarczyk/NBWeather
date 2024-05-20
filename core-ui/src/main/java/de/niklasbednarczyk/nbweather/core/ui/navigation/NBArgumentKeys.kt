package de.niklasbednarczyk.nbweather.core.ui.navigation

object NBArgumentKeys {

    data object ForecastTime : NBArgumentKeyItem<Long> {

        override val key: String = "forecast_time"

        override fun toValue(string: String?): Long? {
            return string?.toLongOrNull()
        }

    }

    data object IsStartDestination : NBArgumentKeyItem<Boolean> {

        override val key: String =  "is_start_destination"

        override fun toValue(string: String?): Boolean? {
            return string?.toBooleanStrictOrNull()
        }

    }

    data object Latitude : NBArgumentKeyItem<Double> {

        override val key: String = "latitude"

        override fun toValue(string: String?): Double? {
            return string?.toDoubleOrNull()
        }

    }

    data object Longitude : NBArgumentKeyItem<Double> {

        override val key: String = "longitude"

        override fun toValue(string: String?): Double? {
            return string?.toDoubleOrNull()
        }

    }

}