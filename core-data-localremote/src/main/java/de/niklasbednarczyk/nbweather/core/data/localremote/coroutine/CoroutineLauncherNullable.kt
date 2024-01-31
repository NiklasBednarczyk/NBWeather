package de.niklasbednarczyk.nbweather.core.data.localremote.coroutine

abstract class CoroutineLauncherNullable<T> : CoroutineLauncher<T?>() {

    override fun onFailed(): T? {
        return null
    }

}