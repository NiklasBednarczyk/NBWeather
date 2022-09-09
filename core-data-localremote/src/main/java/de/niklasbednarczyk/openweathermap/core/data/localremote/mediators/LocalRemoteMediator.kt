package de.niklasbednarczyk.openweathermap.core.data.localremote.mediators

import android.util.Log
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.ErrorType
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.core.data.localremote.constants.ConstantsCoreLocalRemote
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.models.ModelLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.net.UnknownHostException

abstract class LocalRemoteMediator<LocalRemote, Local : ModelLocal, Remote> {

    protected abstract fun getLocal(): Flow<Local?>

    protected abstract suspend fun getRemote(): Remote

    protected abstract fun clearLocal(local: Local)

    protected abstract fun insertLocal(remote: Remote)

    protected abstract fun localToLocalRemote(local: Local): LocalRemote

    protected abstract fun shouldGetRemote(local: Local): Boolean

    suspend operator fun invoke(): Flow<Resource<LocalRemote>> = flow {
        emit(Resource.Loading)

        val localFirst = getLocal().firstOrNull()

        val flow: Flow<Resource<LocalRemote>> =
            if (localFirst == null || localFirst.metadata.isExpired || shouldGetRemote(localFirst)) {
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

    private fun onRemoteFailed(throwable: Throwable): Resource<LocalRemote> {
        //TODO (#5) Better logging
        Log.e(ConstantsCoreLocalRemote.Logging.TAG, throwable.message.toString())
        val type = when (throwable) {
            is UnknownHostException -> ErrorType.NO_INTERNET
            else -> null
        }
        return Resource.Error(type)
    }


    private fun onLocalFailed(): Resource<LocalRemote> {
        //TODO (#5) Better logging
        Log.e(ConstantsCoreLocalRemote.Logging.TAG, "Query failed")
        return Resource.Error(ErrorType.QUERY_FAILED)
    }


}