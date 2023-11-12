package de.niklasbednarczyk.nbweather.test.ui.screens

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest

interface NBViewModelTest : NBTest {

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