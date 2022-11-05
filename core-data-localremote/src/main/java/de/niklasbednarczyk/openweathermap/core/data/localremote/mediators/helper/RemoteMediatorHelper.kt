package de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.helper

import android.util.Log
import de.niklasbednarczyk.openweathermap.core.data.localremote.constants.ConstantsCoreLocalRemote
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmErrorType
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import java.net.UnknownHostException

internal interface RemoteMediatorHelper<Data, Remote> {

    suspend fun getRemote(): Remote

    fun onRemoteFailed(throwable: Throwable): OwmResource<Data> {
        //TODO (#5) Better logging
        Log.e(ConstantsCoreLocalRemote.Logging.TAG, throwable.message.toString())
        val type = when (throwable) {
            is UnknownHostException -> OwmErrorType.NO_INTERNET
            else -> null
        }
        return OwmResource.Error(type)
    }

}