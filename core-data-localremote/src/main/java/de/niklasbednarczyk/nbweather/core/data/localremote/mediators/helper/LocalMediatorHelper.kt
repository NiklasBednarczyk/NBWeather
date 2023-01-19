package de.niklasbednarczyk.nbweather.core.data.localremote.mediators.helper

import de.niklasbednarczyk.nbweather.core.common.logging.nbLogError
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import kotlinx.coroutines.flow.Flow

internal interface LocalMediatorHelper<Data, Local> {

    fun getLocal(): Flow<Local?>

    fun localToData(local: Local): Data

    fun onLocalFailed(throwable: Throwable): NBResource<Data> {
        nbLogError(throwable)
        return NBResource.Error()
    }

}