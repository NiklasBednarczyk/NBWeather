package de.niklasbednarczyk.nbweather.core.data.localremote.coroutine

abstract class CoroutineLauncherIsSuccessful : CoroutineLauncher<Boolean>() {

    override fun onFailed(): Boolean {
        return false
    }

}