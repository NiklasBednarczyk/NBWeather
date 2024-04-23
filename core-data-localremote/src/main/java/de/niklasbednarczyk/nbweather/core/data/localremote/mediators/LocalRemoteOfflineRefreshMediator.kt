package de.niklasbednarczyk.nbweather.core.data.localremote.mediators

import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.helper.LocalRemoteOfflineHelper
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

abstract class LocalRemoteOfflineRefreshMediator<Local, Remote> :
    LocalRemoteOfflineHelper<Unit, Local, Remote> {

    override fun localToData(local: Local) {
        return
    }

    suspend operator fun invoke(): NBResource<Unit> = withContext(Dispatchers.IO) {

        val localFirst = getLocal().firstOrNull()

        try {
            val remote = getRemote()
            insertLocal(remote)
            if (localFirst != null) {
                clearLocal(localFirst)
            }
            onSuccess(getLocal().firstOrNull())
        } catch (throwable: Throwable) {
            onRemoteFailed(throwable)
        }

    }

}