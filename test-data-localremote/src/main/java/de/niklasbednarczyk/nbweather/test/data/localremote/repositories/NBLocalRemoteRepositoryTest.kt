package de.niklasbednarczyk.nbweather.test.data.localremote.repositories

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transformWhile

interface NBLocalRemoteRepositoryTest : NBTest {

    suspend fun <T> Flow<NBResource<T>?>.assertResourceSuccess(
        assert: suspend (dataAct: T) -> Unit
    ) {
        transformWhile { resource ->
            emit(resource)
            resource !is NBResource.Success
        }.collect { resource ->
            if (resource is NBResource.Success) {
                assert(resource.data)
            }
        }
    }

}