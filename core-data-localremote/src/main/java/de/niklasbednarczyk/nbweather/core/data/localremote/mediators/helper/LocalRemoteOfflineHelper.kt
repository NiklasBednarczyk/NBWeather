package de.niklasbednarczyk.nbweather.core.data.localremote.mediators.helper

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource

internal interface LocalRemoteOfflineHelper<Data, Local, Remote> : LocalMediatorHelper<Data, Local>, RemoteMediatorHelper<Data, Remote> {

    fun clearLocal(local: Local)

    fun insertLocal(remote: Remote)

    fun onSuccess(local: Local?): NBResource<Data> {
        return if (local != null) {
            NBResource.Success(localToData(local))
        } else {
            onLocalFailed(RuntimeException("Query failed"))
        }
    }

}