package de.niklasbednarczyk.openweathermap.core.data.localremote.remote.constants

import de.niklasbednarczyk.openweathermap.core.data.localremote.remote.BuildConfig


object ConstantsCoreRemote {

    object ImageUrl {
        const val PREFIX = "https://openweathermap.org/img/wn/"
        const val SUFFIX = "@2x.png"
    }

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

        object Limit {
            const val NAME = "limit"
            const val VALUE = 5
        }

        object LocationName {
            const val NAME = "q"
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