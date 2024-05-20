package de.niklasbednarczyk.nbweather.core.data.localremote.mediators

import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.helper.LocalRemoteOfflineHelper
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class LocalRemoteOfflineGetMediator<Data, Local, Remote> :
    LocalRemoteOfflineHelper<Data, Local, Remote> {

    protected abstract fun shouldGetRemote(local: Local): Boolean

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
                    getLocal().map(::onSuccess)
                } catch (throwable: Throwable) {
                    flowOf(onRemoteFailed(throwable))
                }
            } else {
                getLocal().map(::onSuccess)
            }

        emitAll(flow)

    }.flowOn(Dispatchers.IO)


}