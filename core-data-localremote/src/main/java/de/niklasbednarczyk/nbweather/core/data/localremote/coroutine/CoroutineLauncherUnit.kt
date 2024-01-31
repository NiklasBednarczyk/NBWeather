package de.niklasbednarczyk.nbweather.core.data.localremote.coroutine

abstract class CoroutineLauncherUnit : CoroutineLauncher<Unit>() {

    override fun onFailed() {
        return
    }

}