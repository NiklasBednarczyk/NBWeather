package de.niklasbednarczyk.nbweather.test.ui.screens

import androidx.lifecycle.SavedStateHandle
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeyItem
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest

interface NBViewModelTest : NBTest {

    fun createTestSaveStateHandle(
        vararg pairs: Pair<NBArgumentKeyItem<*>, *>
    ): SavedStateHandle {
        val map = pairs.associate { pair ->
            pair.first.key to NBArgumentKeyItem.toString(pair.second)
        }

        return SavedStateHandle(map)
    }

    fun <T> assertResourceIsSuccess(
        resource: NBResource<T>?
    ) {
        assertIsClass(resource, NBResource.Success::class.java)
    }

    fun <T> assertResourceIsError(
        resource: NBResource<T>?
    ) {
        assertIsClass(resource, NBResource.Error::class.java)
    }

    fun <T> assertResourceIsLoading(
        resource: NBResource<T>?
    ) {
        assertIsClass(resource, NBResource.Loading::class.java)
    }

}