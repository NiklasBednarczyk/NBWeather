package de.niklasbednarczyk.openweathermap.core.data.localremote.mediators

import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.helper.LocalMediatorHelper
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class LocalMediator<Data, Local> : LocalMediatorHelper<Data, Local> {

    private fun mapToResource(local: Local?): Resource<Data?> {
        val data = if (local != null) localToData(local) else null
        return Resource.Success(data)
    }

    operator fun invoke(): Flow<Resource<Data?>> = getLocal()
        .map { local ->
            mapToResource(local)
        }
        .onStart { emit(Resource.Loading) }
        .catch { emit(onLocalFailed()) }
        .flowOn(Dispatchers.IO)

}