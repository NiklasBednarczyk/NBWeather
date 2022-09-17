package de.niklasbednarczyk.openweathermap.core.data.localremote.mediators

import android.util.Log
import de.niklasbednarczyk.openweathermap.core.data.localremote.constants.ConstantsCoreLocalRemote
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.models.ModelLocal
import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.helper.RemoteMediatorHelper
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.ErrorType
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class LocalRemoteMediator<LocalRemote, Local : ModelLocal, Remote> :
    RemoteMediatorHelper<LocalRemote, Remote> {

    protected abstract fun getLocal(): Flow<Local?>

    protected abstract fun clearLocal(local: Local)

    protected abstract fun insertLocal(remote: Remote)

    protected abstract fun localToLocalRemote(local: Local): LocalRemote

    protected open fun shouldGetRemoteSpecialCase(local: Local): Boolean {
        return false
    }

    suspend operator fun invoke(): Flow<Resource<LocalRemote>> = flow {
        emit(Resource.Loading)

        val localFirst = getLocal().firstOrNull()

        val flow: Flow<Resource<LocalRemote>> =
            if (localFirst == null || localFirst.metadata.isExpired || shouldGetRemoteSpecialCase(
                    localFirst
                )
            ) {
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

    private fun onSuccess(local: Local?): Resource<LocalRemote> {
        return if (local != null) {
            Resource.Success(localToLocalRemote(local))
        } else {
            onLocalFailed()
        }
    }

    private fun onLocalFailed(): Resource<LocalRemote> {
        //TODO (#5) Better logging
        Log.e(ConstantsCoreLocalRemote.Logging.TAG, "Query failed")
        return Resource.Error(ErrorType.QUERY_FAILED)
    }


}