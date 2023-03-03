package de.niklasbednarczyk.nbweather.core.ui.navigation.drawer

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModelChannel

@HiltViewModel
class NBNavigationDrawerViewModel : NBViewModelChannel<NBNavigationDrawerEventType>() {

    fun openDrawer() {
        send(NBNavigationDrawerEventType.OPEN)
    }

    fun closeDrawer() {
        send(NBNavigationDrawerEventType.CLOSE)
    }

}