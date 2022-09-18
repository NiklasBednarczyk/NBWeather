package de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.helper

import android.util.Log
import de.niklasbednarczyk.openweathermap.core.data.localremote.constants.ConstantsCoreLocalRemote
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.ErrorType
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import java.net.UnknownHostException

internal interface RemoteMediatorHelper<Data, Remote> {

    suspend fun getRemote(): Remote

    fun onRemoteFailed(throwable: Throwable): Resource<Data> {
        //TODO (#5) Better logging
        Log.e(ConstantsCoreLocalRemote.Logging.TAG, throwable.message.toString())
        val type = when (throwable) {
            is UnknownHostException -> ErrorType.NO_INTERNET
            else -> null
        }
        return Resource.Error(type)
    }

}