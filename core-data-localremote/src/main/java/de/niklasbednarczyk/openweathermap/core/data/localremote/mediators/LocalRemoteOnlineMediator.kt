package de.niklasbednarczyk.openweathermap.core.data.localremote.mediators

import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.helper.RemoteMediatorHelper
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class LocalRemoteOnlineMediator<Data, Remote> : RemoteMediatorHelper<Data, Remote> {

    protected abstract fun insertLocal(remote: Remote)

    protected abstract fun remoteToData(remote: Remote): Data

    operator fun invoke(): Flow<Resource<Data>> = flow {
        emit(Resource.Loading)

        val flow: Flow<Resource<Data>> = try {
            val remote = getRemote()
            insertLocal(remote)
            flowOf(Resource.Success(remoteToData(remote)))
        } catch (throwable: Throwable) {
            flowOf(onRemoteFailed(throwable))
        }

        emitAll(flow)

    }.flowOn(Dispatchers.IO)

}