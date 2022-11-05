package de.niklasbednarczyk.openweathermap.core.data.localremote.mediators

import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.helper.LocalMediatorHelper
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class LocalMediator<Data, Local> : LocalMediatorHelper<Data, Local> {

    private fun mapToResource(local: Local?): OwmResource<Data?> {
        val data = if (local != null) localToData(local) else null
        return OwmResource.Success(data)
    }

    operator fun invoke(): Flow<OwmResource<Data?>> = getLocal()
        .map { local ->
            mapToResource(local)
        }
        .onStart { emit(OwmResource.Loading) }
        .catch { emit(onLocalFailed()) }
        .flowOn(Dispatchers.IO)

}