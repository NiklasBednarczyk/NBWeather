package de.niklasbednarczyk.nbweather.core.data.localremote.mediators.helper

import android.util.Log
import de.niklasbednarczyk.nbweather.core.data.localremote.constants.ConstantsCoreLocalRemote
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import kotlinx.coroutines.flow.Flow

internal interface LocalMediatorHelper<Data, Local> {

    fun getLocal(): Flow<Local?>

    fun localToData(local: Local): Data

    fun onLocalFailed(): NBResource<Data> {
        //TODO (#5) Better logging
        Log.e(ConstantsCoreLocalRemote.Logging.TAG, "Query failed")
        return NBResource.Error()
    }

}