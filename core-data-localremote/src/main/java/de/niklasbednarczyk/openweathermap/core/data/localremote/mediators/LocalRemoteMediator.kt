package de.niklasbednarczyk.openweathermap.core.data.localremote.mediators

import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.helper.LocalMediatorHelper
import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.helper.RemoteMediatorHelper
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class LocalRemoteMediator<Data, Local, Remote> :
    LocalMediatorHelper<Data, Local>, RemoteMediatorHelper<Data, Remote> {

    protected abstract fun clearLocal(local: Local)

    protected abstract fun insertLocal(remote: Remote)

    protected abstract fun shouldGetRemote(local: Local): Boolean

    suspend operator fun invoke(): Flow<Resource<Data>> = flow {
        emit(Resource.Loading)

        val localFirst = getLocal().firstOrNull()

        val flow: Flow<Resource<Data>> =
            if (localFirst == null || shouldGetRemote(localFirst)) {
                try {
                    val remote = getRemote()
                    if (localFirst != null) {
                        clearLocal(localFirst)
                    }
                    insertLocal(remote)
                    getLocal().map { local -> onSuccess(local) }
                } catch (throwable: Throwable) {
                    flowOf(onRemoteFailed(throwable))
                }
            } else {
                getLocal().map { local -> onSuccess(local) }
            }

        emitAll(flow)

    }.flowOn(Dispatchers.IO)


}