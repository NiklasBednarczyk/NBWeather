package de.niklasbednarczyk.nbweather.core.data.localremote.mediators

import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.helper.LocalMediatorHelper
import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.helper.RemoteMediatorHelper
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class LocalRemoteOfflineMediator<Data, Local, Remote> :
    LocalMediatorHelper<Data, Local>, RemoteMediatorHelper<Data, Remote> {

    protected abstract fun clearLocal(local: Local)

    protected abstract fun insertLocal(remote: Remote)

    protected abstract fun shouldGetRemote(local: Local): Boolean

    private fun onSuccess(local: Local?): NBResource<Data> {
        return if (local != null) {
            NBResource.Success(localToData(local))
        } else {
            onLocalFailed()
        }
    }

    suspend operator fun invoke(): Flow<NBResource<Data>> = flow {
        emit(NBResource.Loading)

        val localFirst = getLocal().firstOrNull()

        val flow: Flow<NBResource<Data>> =
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