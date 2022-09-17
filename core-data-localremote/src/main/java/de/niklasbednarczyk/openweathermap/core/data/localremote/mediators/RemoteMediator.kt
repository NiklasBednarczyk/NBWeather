package de.niklasbednarczyk.openweathermap.core.data.localremote.mediators

import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.helper.RemoteMediatorHelper
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class RemoteMediator<LocalRemote, Remote> : RemoteMediatorHelper<LocalRemote, Remote> {

    protected abstract fun remoteToLocalRemote(remote: Remote): LocalRemote

    suspend operator fun invoke(): Flow<Resource<LocalRemote>> = flow {
        emit(Resource.Loading)

        val resource = try {
            val remote = getRemote()
            Resource.Success(remoteToLocalRemote(remote))
        } catch (throwable: Throwable) {
            onRemoteFailed(throwable)
        }

        emit(resource)
    }.flowOn(Dispatchers.IO)

}