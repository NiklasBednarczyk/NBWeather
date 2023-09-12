package de.niklasbednarczyk.nbweather.core.ui.navigation.destination

import android.net.Uri

abstract class NBTopLevelDestination {

    private val authority: String = this::class.java.simpleName.lowercase()

    val route: Uri
        get() = Uri.Builder()
            .authority(authority)
            .build()

    val routeForGraph: String
        get() = route.toString()

    val routeForNavigation: String
        get() = route.toString()

    open val clearBackStack: Boolean = false

}