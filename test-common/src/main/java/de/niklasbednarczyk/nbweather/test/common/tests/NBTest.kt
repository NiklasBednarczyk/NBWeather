package de.niklasbednarczyk.nbweather.test.common.tests

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Before
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

interface NBTest {

    val testScope: TestScope
        get() = TestScope(UnconfinedTestDispatcher())

    val context: Context
        get() = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    fun setLocale(locale: Locale) {
        // update locale for date formatters
        Locale.setDefault(locale)
        // update locale for app resources
        val config = context.resources.configuration
        config.setLocale(locale)
        context.createConfigurationContext(config)
    }

    fun <T> assertValue(
        expected: T?,
        actual: T?,
        nullable: Boolean = false
    ) {
        if (!nullable) {
            assertNotNull(actual)
        }
        assertEquals(expected, actual)
    }

    fun <T> assertListIsNotEmpty(
        actual: List<T>?
    ) {
        assertTrue(actual?.isNotEmpty() == true)
    }

    fun <T> assertListIsEmpty(
        actual: List<T>
    ) {
        assertTrue(actual.isEmpty())
    }

    fun <T> assertListHasSize(
        actual: List<T>,
        size: Int
    ) {
        assertEquals(size, actual.size)
    }

    fun <T> assertListsContainsItemInOrder(
        actual: List<T>,
        vararg items: T
    ) {
        assertEquals(actual, items.toList())
    }

    fun <T> assertListsContainSameItems(
        list1: List<T>,
        list2: List<T>
    ) {
        assertTrue(list1.containsAll(list2))
    }

    fun <T> assertListDoesContain(
        actual: List<T>?,
        vararg items: T
    ) {
        assertTrue(actual?.containsAll(items.toList()) == true)
    }

    fun <T> assertListDoesNotContain(
        actual: List<T>?,
        vararg items: T
    ) {
        assertTrue(actual?.containsAll(items.toList()) == false)
    }

    fun assertNullOrEmpty(
        actual: Any?
    ) {
        if (actual is List<*>?) {
            assertTrue(actual == null || actual.isEmpty())
        } else {
            assertNull(actual)
        }
    }

    fun assertNullOrEmpty(
        actual: String?
    ) {
        assertTrue(actual.isNullOrEmpty())
    }


}