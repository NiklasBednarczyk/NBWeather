package de.niklasbednarczyk.nbweather.core.data.localremote.mediators

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.data.localremote.mediators.helper.LocalMediatorHelper
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class LocalGetMediator<Data, Local> : LocalMediatorHelper<Data, Local> {

    private fun mapToResource(local: Local?): NBResource<Data?> {
        val data = nbNullSafe(local) { localToData(it) }
        return NBResource.Success(data)
    }

    suspend operator fun invoke(): Flow<NBResource<Data?>> = getLocal()
        .map { local ->
            mapToResource(local)
        }
        .onStart { emit(NBResource.Loading) }
        .catch { throwable ->
            emit(onLocalFailed(throwable))
        }
        .flowOn(Dispatchers.IO)

}