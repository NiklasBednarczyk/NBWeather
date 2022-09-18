package de.niklasbednarczyk.openweathermap.core.data.localremote.mediators

import de.niklasbednarczyk.openweathermap.core.data.localremote.mediators.helper.LocalMediatorHelper
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class LocalMediator<Data, Local> : LocalMediatorHelper<Data, Local> {

    operator fun invoke(): Flow<Resource<Data>> = getLocal()
        .map { local ->
            onSuccess(local)
        }
        .onStart { emit(Resource.Loading) }
        .catch { emit(onLocalFailed()) }
        .flowOn(Dispatchers.IO)

}