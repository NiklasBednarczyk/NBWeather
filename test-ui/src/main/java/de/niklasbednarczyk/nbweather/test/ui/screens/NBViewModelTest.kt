package de.niklasbednarczyk.nbweather.test.ui.screens

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest

interface NBViewModelTest : NBTest {

    fun <Item : Any> testDividerList(
        items: List<Item>,
        dividerClassJava: Class<*>,
        headerClassJava: Class<*>? = null
    ) {
        // Arrange + Act
        val firstItem = items.first()
        val lastItem = items.last()

        // Assert
        //      Divider
        assertIsNotClass(firstItem, dividerClassJava)
        assertIsNotClass(lastItem, dividerClassJava)

        assertListConsecutiveItems(items) { item ->
            item.javaClass == dividerClassJava
        }

        //      Header
        if (headerClassJava != null) {
            assertIsClass(firstItem, headerClassJava)
            assertIsNotClass(lastItem, headerClassJava)

            assertListConsecutiveItems(items) { item ->
                item.javaClass == headerClassJava
            }
        }
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