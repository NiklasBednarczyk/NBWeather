package de.niklasbednarczyk.openweathermap.core.data.localremote.remote.constants

import de.niklasbednarczyk.openweathermap.core.data.localremote.remote.BuildConfig


object ConstantsCoreRemote {

    object Query {

        internal object ApiKey {
            const val NAME = "appid"
            const val VALUE = BuildConfig.openweathermapapiKey
        }

        object Language {
            const val NAME = "lang"
        }

        object Latitude {
            const val NAME = "lat"
        }

        object Longitude {
            const val NAME = "lon"
        }

        object Units {
            const val NAME = "units"
        }

    }

    internal object Url {
        const val DATA = "https://api.openweathermap.org/data/2.5/"
        const val GEO = "https://api.openweathermap.org/geo/1.0/"
    }
}