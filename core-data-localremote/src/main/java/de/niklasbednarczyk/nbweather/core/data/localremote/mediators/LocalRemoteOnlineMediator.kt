package de.niklasbednarczyk.nbweather.core.data.localremote.mediators

import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.helper.RemoteMediatorHelper
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class LocalRemoteOnlineMediator<Data, Remote> : RemoteMediatorHelper<Data, Remote> {

    protected abstract fun insertLocal(remote: Remote)

    protected abstract fun remoteToData(remote: Remote): Data

    operator fun invoke(): Flow<NBResource<Data>> = flow {
        emit(NBResource.Loading)

        val flow: Flow<NBResource<Data>> = try {
            val remote = getRemote()
            insertLocal(remote)
            flowOf(NBResource.Success(remoteToData(remote)))
        } catch (throwable: Throwable) {
            flowOf(onRemoteFailed(throwable))
        }

        emitAll(flow)

    }.flowOn(Dispatchers.IO)

}