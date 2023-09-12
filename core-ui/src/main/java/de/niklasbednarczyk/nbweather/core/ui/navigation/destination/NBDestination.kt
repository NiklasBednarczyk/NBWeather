package de.niklasbednarczyk.nbweather.core.ui.navigation.destination

import android.net.Uri

sealed class NBDestination {

    abstract val topLevelDestination: NBTopLevelDestination

    private val path: String = this::class.java.simpleName.lowercase()

    protected val baseRoute: Uri
        get() = topLevelDestination.route.buildUpon()
            .appendPath(path)
            .build()

    abstract val routeForGraph: String

    abstract class WithoutArguments : NBDestination() {

        override val routeForGraph: String
            get() = baseRoute.toString()

        val routeForNavigation: String
            get() = routeForGraph

    }

    abstract class WithArguments : NBDestination() {

        abstract val arguments: List<String>

        override val routeForGraph: String
            get() {
                val builder = baseRoute.buildUpon()
                arguments.forEach { argument ->
                    builder.appendQueryParameter(argument, "{${argument}}")
                }
                return builder.build().toString()
            }

        protected fun createRouteForNavigation(map: Map<String, Any>): String {
            val builder = baseRoute.buildUpon()
            arguments.forEach { argument ->
                val value = map[argument] ?: return@forEach
                builder.appendQueryParameter(argument, value.toString())
            }
            return builder.build().toString()
        }

    }

}
