package de.niklasbednarczyk.nbweather.core.data.localremote.mediators.helper

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBErrorType
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import timber.log.Timber
import java.net.UnknownHostException

internal interface RemoteMediatorHelper<Data, Remote> {

    suspend fun getRemote(): Remote

    fun onRemoteFailed(throwable: Throwable): NBResource<Data> {
        Timber.e(throwable)
        val type = when (throwable) {
            is UnknownHostException -> NBErrorType.NO_INTERNET
            else -> NBErrorType.UNKNOWN
        }
        return NBResource.Error(type)
    }

}