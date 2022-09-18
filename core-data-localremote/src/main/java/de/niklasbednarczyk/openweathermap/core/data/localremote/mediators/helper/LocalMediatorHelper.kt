package de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.helper

import android.util.Log
import de.niklasbednarczyk.openweathermap.core.data.localremote.constants.ConstantsCoreLocalRemote
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.ErrorType
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import kotlinx.coroutines.flow.Flow

internal interface LocalMediatorHelper<Data, Local> {

    fun getLocal(): Flow<Local?>

    fun localToData(local: Local): Data

    fun onSuccess(local: Local?): Resource<Data> {
        return if (local != null) {
            Resource.Success(localToData(local))
        } else {
            onLocalFailed()
        }
    }

    fun onLocalFailed(): Resource<Data> {
        //TODO (#5) Better logging
        Log.e(ConstantsCoreLocalRemote.Logging.TAG, "Query failed")
        return Resource.Error(ErrorType.QUERY_FAILED)
    }

}