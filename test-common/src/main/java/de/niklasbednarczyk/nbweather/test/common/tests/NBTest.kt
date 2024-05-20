package de.niklasbednarczyk.nbweather.test.common.tests

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Before
import java.util.Locale
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
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

    fun delayForDifferentTimestamps() {
        runBlocking {
            delay(2000L)
        }
    }

    fun <T : Any> List<*>.getFirstItemFromList(
        klass: Class<T>
    ): T {
        return filterIsInstance(klass).first()
    }

    fun <T> assertValue(
        expected: T?,
        actual: T?
    ) {
        assertNotNull(expected)
        assertNotNull(actual)
        assertEquals(expected, actual)
    }

    fun <T1, T2> assertListValues(
        list1: List<T1>?,
        list2: List<T2>?,
        assertValue: (value1: T1, value2: T2) -> Unit
    ) {
        assertNotNull(list1)
        assertNotNull(list2)
        assertListIsNotEmpty(list1)
        assertListIsNotEmpty(list2)
        list1.zip(list2, assertValue)
    }

    fun <T> assertListIsNotEmpty(
        actual: List<T>?
    ) {
        assertTrue(actual?.isNotEmpty() == true)
    }

    fun <T> assertListIsEmpty(
        actual: List<T>?
    ) {
        assertTrue(actual?.isEmpty() == true)
    }

    fun <T> assertListHasSize(
        actual: List<T>?,
        size: Int
    ) {
        assertEquals(size, actual?.size)
    }

    fun <T1, T2> assertListsHaveSameSize(
        list1: List<T1>,
        list2: List<T2>
    ) {
        assertEquals(list1.size, list2.size)
    }

    fun <T> assertListsContainsItemInOrder(
        actual: List<T>,
        vararg items: T
    ) {
        assertEquals(actual, items.toList())
    }

    fun <T> assertListsContainSameItems(
        list1: List<T>?,
        list2: List<T>?
    ) {
        assertNotNull(list1)
        assertNotNull(list2)
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

    fun <T> assertListConsecutiveItems(
        actual: List<T>,
        predicate: (T) -> Boolean
    ) {
        actual.windowed(2).forEach { items ->
            assertFalse(items.all(predicate))
        }
    }

    fun <T, C : Any> assertListDoesContainClass(
        actual: List<T>?,
        klass: Class<C>,
        size: Int
    ) {
        val actualFiltered = actual?.filterIsInstance(klass)
        assertListHasSize(actualFiltered, size)
    }

    fun <T, C : Any> assertListDoesContainClassOnce(
        actual: List<T>?,
        klass: Class<C>
    ) {
        assertListDoesContainClass(
            actual = actual,
            klass = klass,
            size = 1
        )
    }


    fun <T, C : Any> assertListDoesNotContainClass(
        actual: List<T>?,
        klass: Class<C>
    ) {
        assertTrue(actual?.none { item -> klass.isInstance(item) } == true)
    }

    fun <K, T> assertMapIsEmpty(
        actual: Map<K, T>?
    ) {
        assertTrue(actual?.isEmpty() == true)
    }

    fun <K, T> assertMapIsNotEmpty(
        actual: Map<K, T>?
    ) {
        assertTrue(actual?.isNotEmpty() == true)
    }

    fun assertNullOrEmpty(
        actual: Any?
    ) {
        if (actual is List<*>?) {
            assertTrue(actual.isNullOrEmpty())
        } else {
            assertNull(actual)
        }
    }

    fun assertNullOrEmpty(
        actual: String?
    ) {
        assertTrue(actual.isNullOrEmpty())
    }

    fun assertIsClass(
        actual: Any?,
        klass: Class<*>
    ) {
        assertTrue(klass.isInstance(actual))
    }

    fun assertIsNotClass(
        actual: Any?,
        klass: Class<*>
    ) {
        assertFalse(klass.isInstance(actual))
    }

    fun <Item : Any> testDividerList(
        items: List<Item>,
        dividerKlass: Class<*>,
        headerKlass: Class<*>? = null
    ) {
        // Arrange + Act
        val firstItem = items.first()
        val lastItem = items.last()

        // Assert
        //      Divider
        assertIsNotClass(firstItem, dividerKlass)
        assertIsNotClass(lastItem, dividerKlass)

        assertListConsecutiveItems(items) { item ->
            item.javaClass == dividerKlass
        }

        //      Header
        if (headerKlass != null) {
            assertIsClass(firstItem, headerKlass)
            assertIsNotClass(lastItem, headerKlass)

            assertListConsecutiveItems(items) { item ->
                item.javaClass == headerKlass
            }
        }
    }

}