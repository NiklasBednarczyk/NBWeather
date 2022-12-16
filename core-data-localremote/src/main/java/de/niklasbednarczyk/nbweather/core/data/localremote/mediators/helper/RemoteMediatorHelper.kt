package de.niklasbednarczyk.nbweather.core.data.localremote.mediators.helper

import android.util.Log
import de.niklasbednarczyk.nbweather.core.data.localremote.constants.ConstantsCoreLocalRemote
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBErrorType
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import java.net.UnknownHostException

internal interface RemoteMediatorHelper<Data, Remote> {

    suspend fun getRemote(): Remote

    fun onRemoteFailed(throwable: Throwable): NBResource<Data> {
        //TODO (#5) Better logging
        Log.e(ConstantsCoreLocalRemote.Logging.TAG, throwable.message.toString())
        val type = when (throwable) {
            is UnknownHostException -> NBErrorType.NO_INTERNET
            else -> NBErrorType.UNKNOWN
        }
        return NBResource.Error(type)
    }

}