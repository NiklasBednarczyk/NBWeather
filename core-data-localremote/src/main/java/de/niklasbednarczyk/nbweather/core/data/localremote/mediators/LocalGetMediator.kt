package de.niklasbednarczyk.nbweather.core.data.localremote.mediators

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.helper.LocalMediatorHelper
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class LocalGetMediator<Data, Local> : LocalMediatorHelper<Data, Local> {

    private fun mapToResource(local: Local?): NBResource<Data?> {
        val data = nbNullSafe(local) { l -> localToData(l) }
        return NBResource.Success(data)
    }

    protected open fun updateLocal(local: Local) {
        return
    }

    suspend operator fun invoke(): Flow<NBResource<Data?>> = flow {
        emit(NBResource.Loading)

        val flow: Flow<NBResource<Data?>> = try {
            val localFirst = getLocal().firstOrNull()
            if (localFirst != null) {
                updateLocal(localFirst)
            }
            getLocal().map(::mapToResource)
        } catch (throwable: Throwable) {
            flowOf(onLocalFailed(throwable))
        }

        emitAll(flow)
    }.flowOn(Dispatchers.IO)

}