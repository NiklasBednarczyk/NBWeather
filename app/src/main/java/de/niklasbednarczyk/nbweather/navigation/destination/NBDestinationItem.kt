package de.niklasbednarczyk.nbweather.navigation.destination

import android.net.Uri
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeyItem

sealed interface NBDestinationItem {

    val featureName: String

    val destinationName: String

    val routeForGraph: String

    val navArguments: List<NamedNavArgument>

    val baseRoute: Uri
        get() = Uri.Builder()
            .appendPath(featureName)
            .appendPath(destinationName)
            .build()

    interface WithoutArguments : NBDestinationItem {

        override val routeForGraph: String
            get() = baseRoute.toString()

        override val navArguments: List<NamedNavArgument>
            get() = emptyList()

        val routeForNavigation: String
            get() = routeForGraph

    }

    interface WithArguments : NBDestinationItem {

        val argumentKeys: List<NBArgumentKeyItem<*>>

        override val routeForGraph: String
            get() {
                val builder = baseRoute.buildUpon()
                argumentKeys.forEach { argumentKey ->
                    val key = argumentKey.key
                    builder.appendQueryParameter(key, "{${key}}")
                }
                return builder.build().toString()
            }

        override val navArguments: List<NamedNavArgument>
            get() = argumentKeys.map(::createNavArgument)

        fun createNavArgument(
            argumentKey: NBArgumentKeyItem<*>,
            defaultValue: Any? = null
        ): NamedNavArgument {
            return navArgument(argumentKey.key) {
                type = NavType.StringType
                this.defaultValue = NBArgumentKeyItem.toString(defaultValue)
            }
        }

        fun createRouteForNavigation(map: Map<NBArgumentKeyItem<*>, Any?>): String {
            val builder = baseRoute.buildUpon()
            argumentKeys.forEach { argumentKey ->
                val valueString = NBArgumentKeyItem.toString(map[argumentKey])
                builder.appendQueryParameter(argumentKey.key, valueString)
            }
            return builder.build().toString()
        }

    }

}