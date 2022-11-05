package de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.helper

import android.util.Log
import de.niklasbednarczyk.openweathermap.core.data.localremote.constants.ConstantsCoreLocalRemote
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import kotlinx.coroutines.flow.Flow

internal interface LocalMediatorHelper<Data, Local> {

    fun getLocal(): Flow<Local?>

    fun localToData(local: Local): Data

    fun onLocalFailed(): OwmResource<Data> {
        //TODO (#5) Better logging
        Log.e(ConstantsCoreLocalRemote.Logging.TAG, "Query failed")
        return OwmResource.Error()
    }

}