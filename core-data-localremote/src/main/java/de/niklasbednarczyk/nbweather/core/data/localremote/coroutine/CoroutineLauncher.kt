package de.niklasbednarczyk.nbweather.core.data.localremote.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class CoroutineLauncher<T> {

    abstract suspend fun launchSuspend(): T

    abstract fun onFailed(): T

    suspend operator fun invoke(): T {
        return withContext(Dispatchers.IO) {
            try {
                launchSuspend()
            } catch (throwable: Throwable) {
                Timber.e(throwable)
                onFailed()
            }
        }


    }

}