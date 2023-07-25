package de.niklasbednarczyk.nbweather.core.data.localremote.remote.constants

import de.niklasbednarczyk.nbweather.core.data.localremote.remote.BuildConfig


object ConstantsCoreRemote {

    object Query {

        internal object ApiKey {
            const val NAME = "appid"
            const val VALUE = BuildConfig.openweathermapapiKey
        }

        object Exclude {
            const val NAME = "exclude"
            const val VALUE = ""
        }

        object Language {
            const val NAME = "lang"
            const val VALUE = "en"
        }

        object Latitude {
            const val NAME = "lat"
        }

        object Limit {
            const val NAME = "limit"
            const val VALUE_BY_COORDINATES = 1
            const val VALUE_BY_LOCATION_NAME = 5
        }

        object LocationName {
            const val NAME = "q"
        }

        object Longitude {
            const val NAME = "lon"
        }

        object Units {
            const val NAME = "units"
            const val VALUE = "standard"
        }

    }

    internal object Url {
        const val DATA = "https://api.openweathermap.org/data/2.5/"
        const val GEO = "https://api.openweathermap.org/geo/1.0/"
    }
}