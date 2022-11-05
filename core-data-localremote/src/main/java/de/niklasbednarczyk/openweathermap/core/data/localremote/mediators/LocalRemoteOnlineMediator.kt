package de.niklasbednarczyk.openweathermap.core.data.localremote.mediators

import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.helper.RemoteMediatorHelper
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class LocalRemoteOnlineMediator<Data, Remote> : RemoteMediatorHelper<Data, Remote> {

    protected abstract fun insertLocal(remote: Remote)

    protected abstract fun remoteToData(remote: Remote): Data

    operator fun invoke(): Flow<OwmResource<Data>> = flow {
        emit(OwmResource.Loading)

        val flow: Flow<OwmResource<Data>> = try {
            val remote = getRemote()
            insertLocal(remote)
            flowOf(OwmResource.Success(remoteToData(remote)))
        } catch (throwable: Throwable) {
            flowOf(onRemoteFailed(throwable))
        }

        emitAll(flow)

    }.flowOn(Dispatchers.IO)

}